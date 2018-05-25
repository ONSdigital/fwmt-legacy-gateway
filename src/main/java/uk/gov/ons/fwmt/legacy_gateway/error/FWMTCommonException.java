package uk.gov.ons.fwmt.legacy_gateway.error;

public class FWMTCommonException extends Exception {
  static final long serialVersionUID = 0L;

  protected String makePrefix(ExceptionCode code) {
    return "FWMT-" + code.toString();
  }

  public FWMTCommonException(ExceptionCode code) {
    super(code.toString());
  }

  public FWMTCommonException(ExceptionCode code, String message) {
    super(code.toString() + " " + message);
  }

  public FWMTCommonException(ExceptionCode code, String message, Throwable cause) {
    super(code.toString() + " " + message, cause);
  }

  protected FWMTCommonException(ExceptionCode code, String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(code.toString() + " " + message, cause, enableSuppression, writableStackTrace);
  }

  public FWMTCommonException(ExceptionCode code, Throwable cause) {
    super(code.toString() + " " + cause);
  }
}
