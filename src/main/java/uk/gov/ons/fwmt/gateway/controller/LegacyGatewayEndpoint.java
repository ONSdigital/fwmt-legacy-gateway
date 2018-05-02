/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.StaffSummaryDTO;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyLFSSampleReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyStaffReader;

import javax.xml.bind.DatatypeConverter;
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

  public void assertValidFilename(MultipartFile file, String endpoint) throws Exception {
    String filename = file.getOriginalFilename();
    String[] filenameSplit = filename.split("\\.");
    if (filenameSplit.length != 2) {
      throw new InvalidFileNameException(filename);
    }
    String[] nameSplit = filenameSplit[0].split("_");
    if (nameSplit.length != 3) {
      throw new InvalidFileNameException(filename);
    }
    String fileEndpoint = nameSplit[0];
    String surveyTla = nameSplit[1];
    String timestamp = nameSplit[2];
    boolean timestampValid;
    try {
      DatatypeConverter.parseDateTime(timestamp);
      timestampValid = true;
    } catch (IllegalArgumentException e) {
      timestampValid = false;
    }
    if (!(fileEndpoint.equals(endpoint) &&
        surveyTla.length() == 3 &&
        timestampValid)) {
      throw new InvalidFileNameException(filename);
    }
  }

  @RequestMapping(value = "/sample", method = RequestMethod.POST, consumes = "text/csv", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 415, message = "Unsupported Media Type", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GatewayCommonErrorDTO.class),
  })
  public ResponseEntity<SampleSummaryDTO> sampleREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) throws Exception {

    assertValidFilename(file, "sample");

    // add data to reception table
    LegacyLFSSampleReader reader = new LegacyLFSSampleReader(file.getInputStream());
    Iterator<LegacySampleEntity> iterator = reader.iterator();

    // pull the unprocessed entries out from the exceptions stored in the legacySampleReader
    List<SampleSummaryDTO.UnprocessedEntry> unprocessedEntries = reader.errorList.stream()
        .map(IllegalCSVStructureException::toUnprocessedEntry)
        .collect(Collectors.toList());

    // create the response object
    SampleSummaryDTO sampleSummaryDTO = new SampleSummaryDTO(file.getOriginalFilename(), reader.getProcessedCount(),
        unprocessedEntries);
    return ResponseEntity.ok(sampleSummaryDTO);
  }

  @RequestMapping(value = "/staff", method = RequestMethod.POST, consumes = "text/csv", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 415, message = "Unsupported Media Type", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GatewayCommonErrorDTO.class),
  })
  public ResponseEntity<StaffSummaryDTO> staffREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) throws Exception {

    assertValidFilename(file, "staff");

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
    Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();

    ingesterService.ingestLegacyStaff(iterator);

    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(),
        legacyStaffReader.getProcessedCount());
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
