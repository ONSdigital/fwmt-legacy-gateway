package uk.gov.ons.fwmt.legacy_gateway.error;

public class UnknownUserException extends RuntimeException {
  static final long serialVersionUID = 0L;

  public UnknownUserException(String authNo) {
    super("A user was not found on our system, authNo: " + authNo);
  }
}
