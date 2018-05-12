package uk.gov.ons.fwmt.gateway.error;

public class MediaTypeNotSupportedException extends Exception {
  static final long serialVersionUID = 0L;

  public MediaTypeNotSupportedException(String expected, String given) {
    super("Expected '" + expected + "' but was given '" + given + "'");
  }
}
