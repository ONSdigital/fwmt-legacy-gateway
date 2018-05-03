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
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.StaffSummaryDTO;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.readers.*;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

  public void assertValidFilename(String filename, String endpoint) throws InvalidFileNameException {
    // Ensure filename of form 'A.csv'
    String[] filenameParts = filename.split("\\.");
    if (filenameParts.length != 2 || !("csv".equals(filenameParts[1])))
      throw new InvalidFileNameException(filename, "No 'csv' extension");
    // Ensure filename of the form:
    //  'B_C_D.csv' if endpoint is 'sample'
    //  'B_D.csv' if endpoint is 'staff'
    String[] nameParts = filenameParts[0].split("_");
    String fileEndpoint; // B
    String surveyTla; // C, null if endpoint is 'staff'
    String timestamp; // D
    if ("staff".equals(endpoint)) {
      if (nameParts.length != 2)
        throw new InvalidFileNameException(filename, "Invalid number of underscore-delimited sections, there should be two");
      fileEndpoint = nameParts[0];
      timestamp = nameParts[1];
    } else if ("sample".equals(endpoint)) {
      if (nameParts.length != 3)
        throw new InvalidFileNameException(filename, "Invalid number of underscore-delimited sections, there should be three");
      fileEndpoint = nameParts[0];
      surveyTla = nameParts[1]; // C
      timestamp = nameParts[2];
      // Ensure that section 'C' is a three-character survey name
      if (surveyTla.length() != 3)
        throw new InvalidFileNameException(filename, "Survey name must be three characters long");
    } else {
      throw new IllegalArgumentException("Invalid endpoint - was not 'staff' or 'sample'");
    }
    // Ensure that section 'B' matches our endpoint
    if (!endpoint.equals(fileEndpoint))
      throw new InvalidFileNameException(filename, "Invalid endpoint declaration");
    // Ensure that section 'D' is a valid timestamp
    DateTimeFormatter formatterISO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    DateTimeFormatter formatterISOWindows = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");
    try {
      LocalDateTime.parse(timestamp, formatterISO);
    } catch (DateTimeException e) {
      try {
        LocalDateTime.parse(timestamp, formatterISOWindows);
      } catch (DateTimeException f) {
        throw new InvalidFileNameException(filename, "Invalid timestamp of " + timestamp, f);
      }
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
    log.error("Started REST endpoint");

    SampleReader reader;

    assertValidFilename(file.getOriginalFilename(), "sample");
    assertValidFileMetadata(file);

    // add data to reception table
    if (file.getOriginalFilename().contains("LFS")) {
      reader = new LegacyLFSSampleReader(file.getInputStream());
    } else if (file.getOriginalFilename().contains("GFS")) {
      reader = new LegacyGFFSampleReader(file.getInputStream());
    } else {
      throw new InvalidFileNameException(file.getOriginalFilename(), "Invalid survey type");
    }

    Iterator<LegacySampleEntity> iterator = reader.iterator();
    ingesterService.ingestLegacySample(iterator);

    if (reader.getUnprocessedCSVRows().size() != 0) {
      // TODO handle errors
      log.error("Found a CSV parsing error");
    }
    // pull the unprocessed entries out from the exceptions stored in the legacySampleReader
    List<UnprocessedCSVRow> unprocessedEntries = reader.getUnprocessedCSVRows().stream().collect(Collectors.toList());

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

    assertValidFilename(file.getOriginalFilename(), "staff");
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
