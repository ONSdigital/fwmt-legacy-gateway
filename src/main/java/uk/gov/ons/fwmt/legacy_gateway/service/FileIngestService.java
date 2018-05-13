package uk.gov.ons.fwmt.legacy_gateway.service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.CSVParseResult;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.SampleSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.data.dto.StaffSummaryDTO;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This service handles the checking of file attributes, media types and filenames
 * Then, the service hands off control to the CSVParsingService
 */
@Slf4j
@Service
public class FileIngestService {
  public static final DateTimeFormatter TIMESTAMP_FORMAT_LINUX = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  public static final DateTimeFormatter TIMESTAMP_FORMAT_WINDOWS = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");

  private CSVParsingService csvParsingService;

  @Autowired
  public FileIngestService(CSVParsingService csvParsingService) {
    this.csvParsingService = csvParsingService;
  }

  /**
   * This class describes a filename as seen by the FileIngesterService, split into constituent parts
   */
  @Data
  private static class Filename {
    private String endpoint;
    private LegacySampleSurveyType tla;
    private LocalDateTime timestamp;
  }

  protected void verifyCSVFileMetadata(MultipartFile file) throws MediaTypeNotSupportedException {
    log.info("Began a file metadata check for a file with content " + file.getContentType());

    // // // Check metadata
    if (!"text/csv".equals(file.getContentType())) {
      throw new MediaTypeNotSupportedException(file.getContentType(), "text/csv");
    }

    log.info("Passed a file metadata check");
  }

  protected Filename verifyCSVFilename(String rawFilename, String expectedEndpoint) throws InvalidFileNameException {
    log.info("Began a filename parse for " + rawFilename);

    Filename filename = new Filename();

    // // Check extension
    String[] dotSplit = rawFilename.split("\\.");
    if (dotSplit.length != 2 || !("csv".equals(dotSplit[1]))) {
      throw new InvalidFileNameException(rawFilename, "No 'csv' extension");
    }

    // // Split the filename
    String[] underscoreSplit = dotSplit[0].split("_");

    // // Extract the endpoint
    filename.setEndpoint(underscoreSplit[0]);
    log.debug("File endpoint detected as " + filename.getEndpoint());

    // // Check the endpoint against expectations
    // Ensure that section 'B' matches our endpoint
    if (!expectedEndpoint.equals(filename.getEndpoint())) {
      throw new InvalidFileNameException(rawFilename, "File had an incorrect endpoint of " + filename.getEndpoint());
    }

    switch (filename.getEndpoint()) {
    case "staff":
      if (underscoreSplit.length != 2)
        throw new InvalidFileNameException(rawFilename, "File names for staff should contain one underscore");
      break;
    case "sample":
      if (underscoreSplit.length != 3)
        throw new InvalidFileNameException(rawFilename, "File names for samples should contain two underscores");
      break;
    default:
      throw new IllegalArgumentException("File had an unrecognized endpoint of " + filename.getEndpoint());
    }

    // // Validate the TLA
    // Only for the "sample" endpoint
    switch (filename.getEndpoint()) {
    case "sample":
      String tlaString = underscoreSplit[1];
      log.debug("File TLA detected as " + tlaString);
      switch (tlaString) {
      case "LFS":
        filename.setTla(LegacySampleSurveyType.LFS);
        break;
      case "GFF":
        filename.setTla(LegacySampleSurveyType.GFF);
        break;
      default:
        throw new IllegalArgumentException("File had an unrecognized TLA of " + tlaString);
      }
      break;
    }

    // // Validate the timestamp
    // The timestamp is always the last part of the underscore-delimited section
    String rawTimestamp = underscoreSplit[underscoreSplit.length - 1];
    log.debug("File timestamp detected as " + rawTimestamp);
    try {
      LocalDateTime time = LocalDateTime.parse(rawTimestamp, TIMESTAMP_FORMAT_WINDOWS);
      filename.setTimestamp(time);
    } catch (DateTimeParseException e) {
      log.warn("Failed to parse a windows timestamp, trying a linux timestamp");
      try {
        LocalDateTime time = LocalDateTime.parse(rawTimestamp, TIMESTAMP_FORMAT_LINUX);
        filename.setTimestamp(time);
      } catch (DateTimeParseException f) {
        log.error("Failed to parse a windows timestamp", e);
        log.error("Failed to parse a linux timestamp", f);
        // we throw the exception with cause 'e', only because it is the more likely of the two to have been intended
        throw new InvalidFileNameException(rawFilename, "Invalid timestamp of " + rawTimestamp, e);
      }
    }

    log.info("Passed a filename check");

    return filename;
  }

  public SampleSummaryDTO ingestSampleFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    log.info("Began a sample file ingest");

    // check the file metadata
    verifyCSVFileMetadata(file);

    // check filename
    Filename filename = verifyCSVFilename(file.getOriginalFilename(), "sample");

    // parse csv
    // lines are sent to TM and recorded in the database
    CSVParseResult result = csvParsingService
        .parseLegacySample(new InputStreamReader(file.getInputStream()), filename.getTla());

    // construct reply
    SampleSummaryDTO summary = new SampleSummaryDTO(file.getOriginalFilename(), result.getParsedCount(),
        result.getUnprocessedCSVRows());

    log.info("Ended a sample file ingest");

    return summary;
  }

  public StaffSummaryDTO ingestStaffFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    log.info("Began a staff file ingest");

    // check the file metadata
    verifyCSVFileMetadata(file);

    // check filename
    verifyCSVFilename(file.getOriginalFilename(), "staff");

    // parse csv
    // lines are recorded in the database
    // TODO determine where the 'result' of the staff delta goes
    CSVParseResult result = csvParsingService.parseLegacyStaff(new InputStreamReader(file.getInputStream()));

    // construct reply
    StaffSummaryDTO summary = new StaffSummaryDTO(file.getOriginalFilename(), result.getParsedCount());

    log.info("Ended a staff file ingest");

    return summary;
  }
}
