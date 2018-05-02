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

import java.util.ArrayList;
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

  public ResponseEntity<?> confirmFile(MultipartFile file, String endpoint) throws Exception {
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

  public void parseDateTime(String timestamp) throws IllegalArgumentException {
    List<Integer> indexes = new ArrayList<Integer>();
    for (int index = timestamp.indexOf("-"); index >= 0; index = timestamp.indexOf("-", index + 1)) {
      indexes.add(index);
    }
    StringBuilder newTimestamp = new StringBuilder(timestamp);
    newTimestamp.setCharAt(indexes.get(indexes.size()-1), ':');
    newTimestamp.setCharAt(indexes.get(indexes.size()-2), ':');
    DatatypeConverter.parseDateTime(newTimestamp.toString());
  }

  public boolean confirmFilename(MultipartFile file, String endpoint) throws Exception {
    String filename = file.getOriginalFilename();
    String[] filenameSplit = filename.split("\\.");
    if (filenameSplit.length != 3) {
      throw new InvalidFileNameException(filename);
    }
    // TODO replace these unchecked array accesses
    String[] nameSplit = filenameSplit[0].split("_");
    String fileEndpoint = nameSplit[0];
    String surveyTla = nameSplit[1];
    String timestamp = nameSplit[2];
    boolean timestampValid;
    try {
      parseDateTime(timestamp);
      timestampValid = true;
    } catch (IllegalArgumentException e) {
      timestampValid = false;
    }
    return fileEndpoint.equals(endpoint) &&
        surveyTla.length() == 3 &&
        timestampValid;
  }

  public boolean confirmFiletype(MultipartFile file) {
    String filename = file.getOriginalFilename();
    String[] filenameSplit = filename.split("\\.");
    // TODO replace these unchecked array accesses
    return "csv".equals(filenameSplit[filenameSplit.length - 1]);
  }

  @RequestMapping(value = "/sample", method = RequestMethod.POST, consumes = "text/csv", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 400, message = "Bad Request", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 415, message = "Unsupported Media Type", response = GatewayCommonErrorDTO.class),
      @ApiResponse(code = 500, message = "Internal Server Error", response = GatewayCommonErrorDTO.class),
  })
  public ResponseEntity<SampleSummaryDTO> sampleREST(@RequestParam("file") MultipartFile file,
      RedirectAttributes redirectAttributes) throws Exception {

    ResponseEntity<?> responseEntity = confirmFile(file, "sample");
    if (responseEntity != null) {
      // TODO
    }

    // add data to reception table
    LegacyLFSSampleReader reader = new LegacyLFSSampleReader(file.getInputStream());
    Iterator<LegacySampleEntity> iterator = reader.iterator();

    // pull the unprocessed entries out from the exceptions stored in the
    // legacySampleReader
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

    ResponseEntity<?> responseEntity = confirmFile(file, "staff");
    if (responseEntity != null) {
      // TODO
    }

    // add data to reception table
    LegacyStaffReader legacyStaffReader = new LegacyStaffReader(file.getInputStream());
    Iterator<LegacyStaffEntity> iterator = legacyStaffReader.iterator();

    ingesterService.ingestLegacyStaff(iterator);

    StaffSummaryDTO staffSummaryDTO = new StaffSummaryDTO(file.getOriginalFilename(),
        legacyStaffReader.getProcessedCount());
    return ResponseEntity.ok(staffSummaryDTO);
  }
}
