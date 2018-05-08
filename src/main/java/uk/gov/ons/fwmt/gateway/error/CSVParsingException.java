package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;

import java.util.List;

public class CSVParsingException extends Exception {
  @Getter private final List<String> rows;

  public CSVParsingException(String message, List<String> rows) {
    super(message);
    this.rows = rows;
  }

  public CSVParsingException(String message, List<String> rows, Throwable cause) {
    super(message, cause);
    this.rows = rows;
  }
}
