/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.error.GatewayCommonErrorDTO;
import uk.gov.ons.fwmt.gateway.error.IllegalCSVStructureException;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.StaffSummaryDTO;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyGFFSampleReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyLFSSampleReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyStaffReader;
import uk.gov.ons.fwmt.gateway.utility.readers.SampleReader;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for file upload controller
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@RestController
public class LegacyGatewayEndpoint {

  private final IngesterService ingesterService;

  @Autowired
  public LegacyGatewayEndpoint(IngesterService ingesterService) {
    this.ingesterService = ingesterService;
  }

  public String parseFWMTDateTime(String timestamp) {
    int index2 = timestamp.lastIndexOf('-');
    if (index2 == -1 || index2 < 1)
      return null;
    int index1 = timestamp.lastIndexOf('-', index2 - 1);
    if (index1 == -1)
      return null;
    StringBuffer sb = new StringBuffer(timestamp);
    sb.setCharAt(index1, ':');
    sb.setCharAt(index2, ':');
    return sb.toString();
  }

  // TODO tag these all with descriptive messages of the problems that caused them
  public void assertValidFilename(MultipartFile file, String endpoint) throws InvalidFileNameException {
    String filename = file.getOriginalFilename();
    // one dot, splitting the file name
    String[] filenameSplit = filename.split("\\.");
    if (filenameSplit.length != 2)
         throw new InvalidFileNameException(filename);
    // two underscores, splitting the file name sans extension
    String[] nameSplit = filenameSplit[0].split("_");
    if (nameSplit.length != 3)
         throw new InvalidFileNameException(filename);
    // the first section matches our endpoint
    String fileEndpoint = nameSplit[0];
    if (!endpoint.equals(fileEndpoint))
      throw new InvalidFileNameException(filename);
    // the second section contains only three characters
    String surveyTla = nameSplit[1];
    if (surveyTla.length() != 3)
      throw new InvalidFileNameException(filename);
    // the third section is a valid timestamp
    String timestamp = nameSplit[2];
    String parsedDate = parseFWMTDateTime(timestamp);
    if (parsedDate == null)
      throw new InvalidFileNameException(filename);
    try {
      DatatypeConverter.parseDateTime(parsedDate);
    } catch (IllegalArgumentException e) {
      throw new InvalidFileNameException(filename);
    }
  }

  private void assertValidFileMetadata(MultipartFile file) throws MediaTypeNotSupportedException {
    if (!"text/csv".equals(file.getContentType())) {
      throw new MediaTypeNotSupportedException(new MediaType(file.getContentType()), new MediaType("text/csv"));
    }
  }

  @RequestMapping(value = "/samples", method = RequestMethod.POST, produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 415, message = "Unsupported Media Type", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GatewayCommonErrorDTO.class),
  })
  public ResponseEntity<SampleSummaryDTO> sampleREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    SampleReader reader;

    assertValidFilename(file, "sample");
    assertValidFileMetadata(file);

    // add data to reception table
    if (file.getOriginalFilename().contains("LFS")) {
      reader = new LegacyLFSSampleReader(file.getInputStream());
    } else if (file.getOriginalFilename().contains("LFS")) {
      reader = new LegacyGFFSampleReader(file.getInputStream());
    } else {
      throw new InvalidFileNameException(file.getOriginalFilename());
    }

    Iterator<LegacySampleEntity> iterator = reader.iterator();
    ingesterService.ingestLegacySample(iterator);

    if (reader.getErrorList().size() != 0) {
      // TODO handle errors
      log.error("Found a CSV parsing error");
    }
    // pull the unprocessed entries out from the exceptions stored in the legacySampleReader
    List<SampleSummaryDTO.UnprocessedEntry> unprocessedEntries = reader.getErrorList().stream()
        .map(IllegalCSVStructureException::toUnprocessedEntry)
        .collect(Collectors.toList());

    // create the response object
    SampleSummaryDTO sampleSummaryDTO = new SampleSummaryDTO(file.getOriginalFilename(), reader.getSuccessCount(),
        unprocessedEntries);
    return ResponseEntity.ok(sampleSummaryDTO);
  }

  @RequestMapping(value = "/staff", method = RequestMethod.POST, produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 415, message = "Unsupported Media Type", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GatewayCommonErrorDTO.class),
  })
  public ResponseEntity<StaffSummaryDTO> staffREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) throws Exception {

    assertValidFilename(file, "staff");
    assertValidFileMetadata(file);

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
    Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();

    ingesterService.ingestLegacyStaff(iterator);

    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(),
        legacyStaffReader.getProcessedCount());
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
