package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;

public class IllegalCSVStructureException extends Throwable {
  private static final long serialVersionUID = 1402367242522785524L;

  @Getter
  private final String[] strings;

  @Getter
  private final int lineNumber;

  public IllegalCSVStructureException(String[] strings, int lineNumber) {
    this.strings = strings;
    this.lineNumber = lineNumber;
  }
}
