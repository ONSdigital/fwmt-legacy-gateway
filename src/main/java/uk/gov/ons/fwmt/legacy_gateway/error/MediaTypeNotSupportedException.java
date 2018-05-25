package uk.gov.ons.fwmt.legacy_gateway.error;

public class MediaTypeNotSupportedException extends FWMTCommonException {
  static final long serialVersionUID = 0L;

  public MediaTypeNotSupportedException(String expected, String given) {
    super(ExceptionCode.INVALID_MEDIA_TYPE, "Expected '" + expected + "' but was given '" + given + "'");
  }
}
