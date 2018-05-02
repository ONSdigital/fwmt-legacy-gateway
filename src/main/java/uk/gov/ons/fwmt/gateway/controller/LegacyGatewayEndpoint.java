/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.controller;

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

  public ResponseEntity<?> confirmFile(MultipartFile file, String endpoint) {
    // confirm data is in correct format
    if (!confirmFilename(file, endpoint)) {
      return new ResponseEntity<>(
          "The file name specified in the request does not match the file name format expected by the endpoint",
          HttpStatus.BAD_REQUEST);
    } else if (!confirmFiletype(file)) {
      return new ResponseEntity<>("Recieved non-CSV file", HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    } else {
      return null;
    }

  }

  public boolean confirmFilename(MultipartFile file, String endpoint) {
    // TODO What is this outer try catching?
    try {
      String filename = file.getOriginalFilename();
      String[] filenameSplit = filename.split("\\.");
      String[] nameSplit = filenameSplit[0].split("_");
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
      return fileEndpoint.equals(endpoint) &&
          surveyTla.length() == 3 &&
          timestampValid;
    } catch (Exception e) {
      return false;
    }
  }

  public boolean confirmFiletype(MultipartFile file) {
    try {
      String contentType = file.getContentType();
      String filename = file.getOriginalFilename();
      String[] filenameSplit = filename.split("\\.");
      return "text/csv".equals(contentType) &&
          "text/csv".equals(contentType) &&
          "csv".equals(filenameSplit[filenameSplit.length - 1]);
    } catch (Exception e) {
      return false;
    }    
  }

  @RequestMapping(value = "/samples", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> sampleREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes)
      throws IOException {

    ResponseEntity<?> responseEntity = confirmFile(file, "sample");
    if (responseEntity != null) {
      return responseEntity;
    }

    int rowsIngested;

    SampleReader reader;

    // add data to reception table
    if (file.getOriginalFilename().contains("LFS")) {
      reader = new LegacyLFSSampleReader(file.getInputStream());
    } else {
      reader = new LegacyGFFSampleReader(file.getInputStream());
    }

    Iterator<LegacySampleEntity> iterator = reader.iterator();
    rowsIngested = ingesterService.ingestLegacySample(iterator);

    if (reader.getErrorList().size() != 0) {
      // TODO handle errors
      log.error("Found a CSV parsing error");
    }

    SampleSummaryDTO sampleSummaryDTO = new SampleSummaryDTO(file.getOriginalFilename(), rowsIngested);
    return ResponseEntity.ok(sampleSummaryDTO);
  }

  @RequestMapping(value = "/staff", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> staffREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes)
      throws IOException {

    ResponseEntity<?> responseEntity = confirmFile(file, "staff");
    if (responseEntity != null) {
      return responseEntity;
    }

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
    Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();

    int rowsIngested = ingesterService.ingestLegacyStaff(iterator);

    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(), rowsIngested);
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
