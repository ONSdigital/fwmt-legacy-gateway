package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.data.file_ingest.FileIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.file_ingest.Filename;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;
import uk.gov.ons.fwmt.legacy_gateway.service.FileIngestService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * This service handles the checking of file attributes, media types and filenames
 * Then, the service hands off control to the CSVParsingService
 */
@Slf4j
@Service
public class FileIngestServiceImpl implements FileIngestService {
  public static final DateTimeFormatter TIMESTAMP_FORMAT_LINUX = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  public static final DateTimeFormatter TIMESTAMP_FORMAT_WINDOWS = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");

  @Deprecated
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

    // // Check extension
    String[] dotSplit = rawFilename.split("\\.");
    if (dotSplit.length != 2 || !("csv".equals(dotSplit[1]))) {
      throw new InvalidFileNameException(rawFilename, "No 'csv' extension");
    }

    // // Split the filename
    String[] underscoreSplit = dotSplit[0].split("_");

    // // Extract the endpoint
    String endpoint = underscoreSplit[0];
    log.debug("File endpoint detected as " + endpoint);

    // // Check the endpoint against expectations
    // Ensure that section 'B' matches our endpoint
    if (!expectedEndpoint.equals(endpoint)) {
      throw new InvalidFileNameException(rawFilename, "File had an incorrect endpoint of " + endpoint);
    }

    switch (endpoint) {
    case "staff":
      if (underscoreSplit.length != 2)
        throw new InvalidFileNameException(rawFilename, "File names for staff should contain one underscore");
      break;
    case "sample":
      if (underscoreSplit.length != 3)
        throw new InvalidFileNameException(rawFilename, "File names for samples should contain two underscores");
      break;
    default:
      throw new IllegalArgumentException("File had an unrecognized endpoint of " + endpoint);
    }

    // // Validate the TLA
    // Only for the "sample" endpoint
    LegacySampleSurveyType tla = null;
    if (endpoint.equals("sample")) {
      String tlaString = underscoreSplit[1];
      log.debug("File TLA detected as " + tlaString);
      switch (tlaString) {
      case "LFS":
        tla = LegacySampleSurveyType.LFS;
        break;
      case "GFF":
        tla = LegacySampleSurveyType.GFF;
        break;
      default:
        throw new IllegalArgumentException("File had an unrecognized TLA of " + tlaString);
      }
    }

    // // Validate the timestamp
    // The timestamp is always the last part of the underscore-delimited section
    String rawTimestamp = underscoreSplit[underscoreSplit.length - 1];
    LocalDateTime timestamp;
    log.debug("File timestamp detected as " + rawTimestamp);
    try {
      timestamp = LocalDateTime.parse(rawTimestamp, TIMESTAMP_FORMAT_WINDOWS);
    } catch (DateTimeParseException e) {
      log.warn("Failed to parse a windows timestamp, trying a linux timestamp");
      try {
        timestamp = LocalDateTime.parse(rawTimestamp, TIMESTAMP_FORMAT_LINUX);
      } catch (DateTimeParseException f) {
        log.error("Failed to parse a windows timestamp", e);
        log.error("Failed to parse a linux timestamp", f);
        // we throw the exception with cause 'e', only because it is the more likely of the two to have been intended
        throw new InvalidFileNameException(rawFilename, "Invalid timestamp of " + rawTimestamp, e);
      }
    }

    log.info("Passed a filename check");

    return new Filename(endpoint, tla, timestamp);
  }

  public FileIngest ingestSampleFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    // check filename
    Filename filename = verifyCSVFilename(file.getOriginalFilename(), "sample");

    Reader reader = new InputStreamReader(file.getInputStream());

    return new FileIngest(filename, reader);
  }

  public FileIngest ingestStaffFile(MultipartFile file)
      throws IOException, InvalidFileNameException, MediaTypeNotSupportedException {
    // check filename
    Filename filename = verifyCSVFilename(file.getOriginalFilename(), "staff");

    Reader reader = new InputStreamReader(file.getInputStream());

    return new FileIngest(filename, reader);
  }
}
