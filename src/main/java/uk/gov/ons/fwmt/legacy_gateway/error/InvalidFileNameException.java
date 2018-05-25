package uk.gov.ons.fwmt.legacy_gateway.error;

public class InvalidFileNameException extends FWMTCommonException {
  static final long serialVersionUID = 0L;

  private static String makeMessage(String name, String reason) {
    return "'" + name + "' is not a valid file name" +
        ((reason == null) ? "." : ": " + reason + ".");
  }

  public InvalidFileNameException(String name) {
    super(makeMessage(name, null));
  }

  public InvalidFileNameException(String name, String reason) {
    super(makeMessage(name, reason));
  }

  public InvalidFileNameException(String name, String reason, Exception cause) {
    super(makeMessage(name, reason), cause);
  }
}
