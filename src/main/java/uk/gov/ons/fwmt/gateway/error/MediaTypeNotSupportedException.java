package uk.gov.ons.fwmt.gateway.error;

public class MediaTypeNotSupportedException extends Exception {
  public MediaTypeNotSupportedException(String expected, String given) {
    super("Expected '" + expected + "' but was given '" + given + "'");
  }
}
