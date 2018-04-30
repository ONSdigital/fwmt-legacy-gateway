package uk.gov.ons.fwmt.gateway.error;


public class IllegalCSVStructureException extends Throwable {
  private static final long serialVersionUID = 1402367242522785524L;

  private final String[] strings;

  public IllegalCSVStructureException() {
    this.strings = null;
  }

  public IllegalCSVStructureException(String[] strings) {
    this.strings = strings;
  }
}
