package uk.gov.ons.fwmt.legacy_gateway.error;

import lombok.Getter;

import java.util.List;

public class CSVParsingException extends FWMTCommonException {
  static final long serialVersionUID = 0L;

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
