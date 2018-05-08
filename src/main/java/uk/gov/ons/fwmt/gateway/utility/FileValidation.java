package uk.gov.ons.fwmt.gateway.utility;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;

public class FileValidation {

  public void assertValidFilename(String filename, String endpoint) throws InvalidFileNameException {
    // Ensure filename of form 'A.csv'
    String[] filenameParts = filename.split("\\.");
    if (filenameParts.length != 2 || !("csv".equals(filenameParts[1])))
      throw new InvalidFileNameException(filename, "No 'csv' extension");
    // Ensure filename of the form 'B_C_D.csv'
    String[] nameParts = filenameParts[0].split("_");
    if (nameParts.length != 3)
      throw new InvalidFileNameException(filename,
          "Invalid number of underscore-delimited sections, there should be three");
    // Ensure that section 'B' matches our endpoint
    String fileEndpoint = nameParts[0];
    if (!endpoint.equals(fileEndpoint))
      throw new InvalidFileNameException(filename, "Invalid endpoint declaration");
    // Ensure that section 'C' is a three-character survey name
    String surveyTla = nameParts[1];
    if (surveyTla.length() != 3)
      throw new InvalidFileNameException(filename, "Survey name must be three characters long");
    // the third section is a valid timestamp
    String timestamp = nameParts[2];
    DateTimeFormatter formatterISO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
    DateTimeFormatter formatterISOWindows = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss'Z'");
    try {
      LocalDateTime.parse(timestamp, formatterISO);
    } catch (DateTimeException e) {
      try {
        LocalDateTime.parse(timestamp, formatterISOWindows);
      } catch (DateTimeException f) {
        throw new InvalidFileNameException(filename, "Invalid timestamp of " + timestamp, f);
      }
    }
  }

  public void assertValidFileMetadata(MultipartFile file) throws MediaTypeNotSupportedException {
    if (!"text/csv".equals(file.getContentType())) {
      throw new MediaTypeNotSupportedException(new MediaType(file.getContentType()), new MediaType("text/csv"));
    }
  }
}
