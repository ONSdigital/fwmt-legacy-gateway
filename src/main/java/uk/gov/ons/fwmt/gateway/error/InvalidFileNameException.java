package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;

public class InvalidFileNameException extends Exception {
  @Getter private final String name;
  @Getter private final String reason;

  public InvalidFileNameException(String name) {
    this.name = name;
    this.reason = null;
  }

  public InvalidFileNameException(String name, String reason) {
    this.name = name;
    this.reason = reason;
  }

  @Override public String toString() {
    return "'" + this.getName() + "' is not a valid file name" +
        ((this.reason == null) ? "." : ": " + this.reason + ".");
  }
}
