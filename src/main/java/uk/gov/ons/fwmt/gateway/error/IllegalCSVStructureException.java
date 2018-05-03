package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;
import uk.gov.ons.fwmt.gateway.utility.readers.UnprocessedCSVRow;

public class IllegalCSVStructureException extends Exception {
  private static final long serialVersionUID = 1402367242522785524L;

  @Getter private final String[] strings;
  @Getter private final int lineNumber;
  @Getter private final String reason;

  public IllegalCSVStructureException(String[] strings, int lineNumber, String reason) {
    this.strings = strings;
    this.lineNumber = lineNumber;
    this.reason = reason;
  }

  public UnprocessedCSVRow toUnprocessedEntry() {
    return new UnprocessedCSVRow(this.getStrings(), this.getLineNumber(), this.getReason());
  }
}
