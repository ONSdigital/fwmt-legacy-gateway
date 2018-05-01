package uk.gov.ons.fwmt.gateway.error;

import lombok.Getter;

public class InvalidFileNameException extends Exception {
  @Getter
  private final String name;

  public InvalidFileNameException(String name) {
    this.name = name;
  }
}
