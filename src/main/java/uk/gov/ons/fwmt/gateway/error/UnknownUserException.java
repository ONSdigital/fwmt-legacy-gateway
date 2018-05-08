package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;

public class UnknownUserException extends RuntimeException {
  @Getter private String username;

  public UnknownUserException(String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "A user was not found on our system: " + username;
  }
}
