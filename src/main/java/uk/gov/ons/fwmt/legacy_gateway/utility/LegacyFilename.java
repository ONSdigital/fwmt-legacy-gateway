package uk.gov.ons.fwmt.legacy_gateway.utility;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleSurveyType;
import uk.gov.ons.fwmt.legacy_gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.legacy_gateway.error.MediaTypeNotSupportedException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

/**
 * Legacy systems use the filenames of uploaded samples and staff files to pass data
 * For sample files, the filename is expected to be of the form 'staff_{tla}_{timestamp}.csv
 * For staff files, the filename is expected to be of the form 'staff_{timestamp}.csv
 * Timestamps are expected to be of the form 'YYYY-MM-DDTHH:MM:SSZ', or 'YYYY-MM-DDTHH-MM-SSZ'
 */
@Data
@Slf4j
public class LegacyFilename {
  private static final DateTimeFormatter TIMESTAMP_FORMAT_LINUX = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  private static final DateTimeFormatter TIMESTAMP_FORMAT_WINDOWS = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");

  private final String endpoint;
  private final Optional<LegacySampleSurveyType> tla;
  private final String timestamp;

  // TODO move
  public static void checkMetaData(MultipartFile file) throws MediaTypeNotSupportedException {
    log.info(
        "Beginning a file metadata check for " + file.getOriginalFilename() + " with content " + file.getContentType());

    // // // Check metadata
    if (!"text/csv".equals(file.getContentType())) {
      throw new MediaTypeNotSupportedException(file.getContentType(), "text/csv");
    }
  }

  public LegacyFilename(String filename, String expectedEndpoint)
      throws InvalidFileNameException {
    log.info("Beginning a filename parse for " + filename);

    // // Check extension
    String[] dotSplit = filename.split("\\.");
    if (dotSplit.length != 2 || !("csv".equals(dotSplit[1]))) {
      throw new InvalidFileNameException(filename, "No 'csv' extension");
    }

    // // Extract the endpoint
    String[] underscoreSplit = dotSplit[0].split("_");
    endpoint = underscoreSplit[0];
    log.info("File endpoint detected as " + endpoint);

    // // Check the endpoint against expectations
    // Ensure that section 'B' matches our endpoint
    if (!expectedEndpoint.equals(endpoint)) {
      throw new InvalidFileNameException(filename, "File had an incorrect endpoint of " + endpoint);
    }
    switch (endpoint) {
    case "staff":
      if (underscoreSplit.length != 2)
        throw new InvalidFileNameException(filename, "File names for staff should contain one underscore");
      log.info("File has no TLA");
      tla = Optional.empty();
      timestamp = underscoreSplit[1];
      break;
    case "sample":
      if (underscoreSplit.length != 3)
        throw new InvalidFileNameException(filename, "File names for samples should contain two underscores");
      String tlaString = underscoreSplit[1];
      log.info("File TLA detected as " + tlaString);
      // // Validate the TLA
      switch (tlaString) {
      case "LFS":
        tla = Optional.of(LegacySampleSurveyType.LFS);
        break;
      case "GFF":
        tla = Optional.of(LegacySampleSurveyType.GFF);
        break;
      default:
        throw new IllegalArgumentException("File had an unrecognized TLA of " + tlaString);
      }
      timestamp = underscoreSplit[2];
      break;
    default:
      throw new IllegalArgumentException("File had an unrecognized endpoint of " + endpoint);
    }

    // // Validate the timestamp
    log.info("File timestamp detected as " + timestamp);
    try {
      LocalDateTime.parse(timestamp, TIMESTAMP_FORMAT_LINUX);
    } catch (DateTimeException e) {
      try {
        LocalDateTime.parse(timestamp, TIMESTAMP_FORMAT_WINDOWS);
      } catch (DateTimeException f) {
        throw new InvalidFileNameException(filename, "Invalid timestamp of " + timestamp);
      }
    }
    log.info("Parsed a valid file name as " + this.toString());
  }

}
