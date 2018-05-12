package uk.gov.ons.fwmt.legacy_gateway.error;

import lombok.Getter;

public class UnknownUserException extends RuntimeException {
  static final long serialVersionUID = 0L;

  @Getter private String username;

  public UnknownUserException(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "A user was not found on our system: " + username;
  }
}
