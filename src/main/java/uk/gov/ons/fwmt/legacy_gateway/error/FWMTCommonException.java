package uk.gov.ons.fwmt.legacy_gateway.error;

public class FWMTCommonException extends Exception {
  public FWMTCommonException() {
  }

  public FWMTCommonException(String message) {
    super(message);
  }

  public FWMTCommonException(String message, Throwable cause) {
    super(message, cause);
  }

  protected FWMTCommonException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public FWMTCommonException(Throwable cause) {
    super(cause);
  }
}
