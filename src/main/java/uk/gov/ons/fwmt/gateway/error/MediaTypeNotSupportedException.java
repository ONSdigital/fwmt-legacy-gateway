package uk.gov.ons.fwmt.gateway.error;

import org.springframework.http.MediaType;

public class MediaTypeNotSupportedException extends Exception {
  public MediaTypeNotSupportedException(MediaType mediaType, MediaType mediaType2) {
    super("Expected '" + mediaType.getType() + "' but was given '" + mediaType2.getType() + "'");
  }
}
