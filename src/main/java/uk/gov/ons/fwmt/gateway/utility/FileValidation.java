package uk.gov.ons.fwmt.gateway.utility;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import uk.gov.ons.fwmt.gateway.error.InvalidFileNameException;
import uk.gov.ons.fwmt.gateway.error.MediaTypeNotSupportedException;

@Component
public class FileValidation {

  public void assertValidFilename(String filename, String endpoint) throws InvalidFileNameException {
    // Ensure filename of form 'A.csv'
    String[] filenameParts = filename.split("\\.");
    if (filenameParts.length != 2 || !("csv".equals(filenameParts[1])))
      throw new InvalidFileNameException(filename, "No 'csv' extension");
    // Ensure filename of the form:
    //  'B_C_D.csv' if endpoint is 'sample'
    //  'B_D.csv' if endpoint is 'staff'
    String[] nameParts = filenameParts[0].split("_");
    String fileEndpoint; // B
    String surveyTla; // C, null if endpoint is 'staff'
    String timestamp; // D
    if ("staff".equals(endpoint)) {
      if (nameParts.length != 2)
        throw new InvalidFileNameException(filename, "Invalid number of underscore-delimited sections, there should be two");
      fileEndpoint = nameParts[0];
      timestamp = nameParts[1];
    } else if ("sample".equals(endpoint)) {
      if (nameParts.length != 3)
        throw new InvalidFileNameException(filename, "Invalid number of underscore-delimited sections, there should be three");
      fileEndpoint = nameParts[0];
      surveyTla = nameParts[1]; // C
      timestamp = nameParts[2];
      // Ensure that section 'C' is a three-character survey name
      if (surveyTla.length() != 3)
        throw new InvalidFileNameException(filename, "Survey name must be three characters long");
    } else {
      throw new IllegalArgumentException("Invalid endpoint - was not 'staff' or 'sample'");
    }
    // Ensure that section 'B' matches our endpoint
    if (!endpoint.equals(fileEndpoint))
      throw new InvalidFileNameException(filename, "Invalid endpoint declaration");
    // Ensure that section 'D' is a valid timestamp
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
      throw new MediaTypeNotSupportedException(file.getContentType(), "text/csv");
    }
  }
}
