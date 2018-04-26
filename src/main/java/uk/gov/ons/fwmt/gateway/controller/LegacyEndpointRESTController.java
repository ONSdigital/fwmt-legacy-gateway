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
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyLFSSampleReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyStaffReader;

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
public class LegacyEndpointRESTController {

  private final IngesterService ingesterService;

  @Autowired
  public LegacyEndpointRESTController(IngesterService ingesterService) {
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
  }

  public boolean confirmFiletype(MultipartFile file) {
    String contentType = file.getContentType();
    String filename = file.getOriginalFilename();
    String[] filenameSplit = filename.split("\\.");
    return contentType.equals("text/csv") &&
        contentType.equals("text/csv") &&
        filenameSplit[filenameSplit.length - 1].equals("csv");
  }

  @RequestMapping(value = "/sample", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> sampleREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException {

    ResponseEntity<?> responseEntity = confirmFile(file, "sample");
    if (!responseEntity.equals(null)) {
      return responseEntity;
    }

    // add data to reception table
    LegacyLFSSampleReader legacyLFSSampleReader = new LegacyLFSSampleReader(file.getInputStream());
    Iterator<LegacySampleEntity> iterator = legacyLFSSampleReader.iterator();
    ingesterService.ingestLegacySample(iterator);

    if (legacyLFSSampleReader.errorList.size() != 0) {
      // TODO handle errors
    }

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/staff", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> staffREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException {

    ResponseEntity<?> responseEntity = confirmFile(file, "staff");
    if (!responseEntity.equals(null)) {
      return responseEntity;
    }

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
    Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();
    ingesterService.ingestLegacyStaff(iterator);

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @RequestMapping(value = "/info", method = RequestMethod.GET, produces = "application/json")
  public ResponseEntity<?> infoREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
      throws IOException {

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  // Not Currently Requried
  //    @RequestMapping(value = "/legacy/leavers", method = RequestMethod.POST)
  //    public ResponseEntity<?> leaversREST(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes)
  //            throws IOException {
  //
  //        // confirm data is in correct format
  //        if (!confirmFileType(file)) {
  //            return new ResponseEntity<>("Invalid file format",HttpStatus.I_AM_A_TEAPOT);
  //        }
  //
  //        // add data to reception table
  //        LegacyLeaversReader legacyLeaversReader = new LegacyLeaversReader(file.getInputStream());
  //        Iterator<LegacyLeaverEntity> iterator = legacyLeaversReader.iterator();
  //        ingesterService.ingestLegacyLeavers(iterator);
  //
  //        return new ResponseEntity<>(HttpStatus.CREATED);
  //    }

}
