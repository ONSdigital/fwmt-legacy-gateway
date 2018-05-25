package uk.gov.ons.fwmt.legacy_gateway.error;

public class InvalidFileNameException extends FWMTCommonException {
  static final long serialVersionUID = 0L;

  private static String makeMessage(String name, String reason) {
    return "'" + name + "' is not a valid file name" +
        ((reason == null) ? "." : ": " + reason + ".");
  }

  public InvalidFileNameException(String name) {
    super(ExceptionCode.INVALID_FILE_NAME, makeMessage(name, null));
  }

  public InvalidFileNameException(String name, String reason) {
    super(ExceptionCode.INVALID_FILE_NAME, makeMessage(name, reason));
  }

  public InvalidFileNameException(String name, String reason, Exception cause) {
    super(ExceptionCode.INVALID_FILE_NAME, makeMessage(name, reason), cause);
  }
}
