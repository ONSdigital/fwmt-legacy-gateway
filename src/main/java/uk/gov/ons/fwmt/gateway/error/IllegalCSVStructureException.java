package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;
import uk.gov.ons.fwmt.gateway.representation.SampleSummaryDTO;

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

  public SampleSummaryDTO.UnprocessedEntry toUnprocessedEntry() {
    return new SampleSummaryDTO.UnprocessedEntry(this.getStrings(), this.getLineNumber(), this.getReason());
  }
}
