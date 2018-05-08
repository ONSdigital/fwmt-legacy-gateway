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
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.error.GatewayCommonErrorDTO;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.StaffSummaryDTO;
import uk.gov.ons.fwmt.gateway.representation.UnprocessedCSVRowDTO;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.FileValidation;
import uk.gov.ons.fwmt.gateway.utility.readers.*;

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
  private FileValidation fileValidation;

  @Autowired
  public LegacyGatewayEndpoint(IngesterService ingesterService, FileValidation fileValidation) {
    this.ingesterService = ingesterService;
    this.fileValidation = fileValidation;
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

    LegacyReaderBase reader;

    fileValidation.assertValidFilename(file.getOriginalFilename(), "sample");
    fileValidation.assertValidFileMetadata(file);

    // add data to reception table
    if (file.getOriginalFilename().contains("LFS")) {
      reader = new LegacyLFSSampleReader(file.getInputStream());
    } else if (file.getOriginalFilename().contains("GFF")) {
      reader = new LegacyGFFSampleReader(file.getInputStream());
    } else {
      throw new InvalidFileNameException(file.getOriginalFilename(), "Invalid survey type");
    }

    ingesterService.ingestLegacySample(reader);

    if (reader.getUnprocessedCSVRows().size() != 0) {
      // errors are reported back
      log.error("Found a CSV parsing error");
    }

    // create the response object
    SampleSummaryDTO sampleSummaryDTO = new SampleSummaryDTO(file.getOriginalFilename(), reader.getSuccessCount(),
        reader.getUnprocessedCSVRows());
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

    fileValidation.assertValidFilename(file.getOriginalFilename(), "staff");
    fileValidation.assertValidFileMetadata(file);

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());

    ingesterService.ingestLegacyStaff(legacyStaffReader);

    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(),
        legacyStaffReader.getSuccessCount());
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
