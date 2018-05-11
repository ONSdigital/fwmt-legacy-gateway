/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.gov.ons.fwmt.gateway.entity.internal.csv.CSVParseResult;
import uk.gov.ons.fwmt.gateway.error.GatewayCommonErrorDTO;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.StaffSummaryDTO;
import uk.gov.ons.fwmt.gateway.service.CSVParsingService;
import uk.gov.ons.fwmt.gateway.utility.LegacyFilename;

import java.io.IOException;
import java.io.InputStreamReader;

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

  private final CSVParsingService csvParsingService;

  @Autowired
  public LegacyGatewayEndpoint(CSVParsingService csvParsingService) {
    this.csvParsingService = csvParsingService;
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
    log.info("Entered sample endpoint");

    // check filename
    LegacyFilename filename = new LegacyFilename(file, "sample");

    // parse csv
    // lines are sent to TM and recorded in the database
    CSVParseResult result = csvParsingService
        .parseLegacySample(new InputStreamReader(file.getInputStream()), filename.getTla().get());

    // construct reply
    SampleSummaryDTO sampleSummaryDTO =
        new SampleSummaryDTO(file.getOriginalFilename(), result.getParsedCount(), result.getUnprocessedCSVRows());

    log.info("Exited sample endpoint");
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
    log.info("Entered staff endpoint");

    // check filename
    LegacyFilename filename = new LegacyFilename(file, "sample");

    // parse csv
    // lines are recorded in the database
    // TODO determine where the 'result' of the staff delta goes
    CSVParseResult result = csvParsingService.parseLegacyStaff(new InputStreamReader(file.getInputStream()));

    // construct reply
    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(), result.getParsedCount());

    log.info("Exited staff endpoint");
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
