package uk.gov.ons.fwmt.gateway.utility;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Legacy systems use the filenames of uploaded samples and staff files to pass data
 * For sample files, the filename is expected to be of the form 'staff_{tla}_{timestamp}.csv
 * For staff files, the filename is expected to be of the form 'staff_{timestamp}.csv
 * Timestamps are expected to be of the form 'YYYY-MM-DDTHH:MM:SSZ', or 'YYYY-MM-DDTHH-MM-SSZ'
 */
@Data
public class LegacyFilename {
  private static final DateTimeFormatter TIMESTAMP_FORMAT_LINUX = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
  private static final DateTimeFormatter TIMESTAMP_FORMAT_WINDOWS = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");

  private final String endpoint;
  private final String tla;
  private final String timestamp;

  public LegacyFilename(MultipartFile file, String expectedEndpoint)
      throws InvalidFileNameException, MediaTypeNotSupportedException {
    // // // Check metadata
    if (!"text/csv".equals(file.getContentType())) {
      throw new MediaTypeNotSupportedException(file.getContentType(), "text/csv");
    }
    // // // Check filename
    String filename = file.getOriginalFilename();
    // // Check extension
    String[] dotSplit = filename.split("\\.");
    if (dotSplit.length != 2 || !("csv".equals(dotSplit[1])))
      throw new InvalidFileNameException(filename, "No 'csv' extension");
    // // Extract the endpoint
    String[] underscoreSplit = dotSplit[0].split("_");
    endpoint = underscoreSplit[0];
    // // Check the endpoint against expectations
    // Ensure that section 'B' matches our endpoint
    if (!expectedEndpoint.equals(endpoint))
      throw new InvalidFileNameException(filename, "File had an incorrect endpoint of " + endpoint);
    switch (endpoint) {
    case "staff":
      if (underscoreSplit.length != 2)
        throw new InvalidFileNameException(filename, "File names for staff should contain one underscore");
      tla = null;
      timestamp = underscoreSplit[1];
      break;
    case "sample":
      if (underscoreSplit.length != 3)
        throw new InvalidFileNameException(filename, "File names for samples should contain two underscores");
      tla = underscoreSplit[1];
      timestamp = underscoreSplit[2];
      break;
    default:
      throw new IllegalArgumentException("File had an unrecognized endpoint of " + endpoint);
    }
    // // Validate the TLA, if it exists
    if (tla != null) {
      if (tla.length() != 3)
        throw new InvalidFileNameException(filename, "Survey TLA must be three characters long");
    }
    // // Validate the timestamp
    try {
      LocalDateTime.parse(timestamp, TIMESTAMP_FORMAT_LINUX);
    } catch (DateTimeException e) {
      try {
        LocalDateTime.parse(timestamp, TIMESTAMP_FORMAT_WINDOWS);
      } catch (DateTimeException f) {
        throw new InvalidFileNameException(filename, "Invalid timestamp of " + timestamp);
      }
    }
  }

}
