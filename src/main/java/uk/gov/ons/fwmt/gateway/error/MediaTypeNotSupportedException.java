package uk.gov.ons.fwmt.gateway.error;

import org.springframework.http.MediaType;

public class MediaTypeNotSupportedException extends Exception {
  public MediaTypeNotSupportedException(MediaType given, MediaType expected) {
    super("Expected " + expected.toString() + " but was given " + given.toString());
  }
}
