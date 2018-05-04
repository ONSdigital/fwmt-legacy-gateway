package uk.gov.ons.fwmt.gateway.error;

import org.springframework.http.MediaType;

public class MediaTypeNotSupportedException extends Exception {
  public MediaTypeNotSupportedException(String given, String expected) {
    super("Expected " + expected + " but was given " + given);
  }
}
