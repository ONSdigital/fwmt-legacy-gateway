package uk.gov.ons.fwmt.gateway.utility.readers;

public class IllegalCSVStructureException extends Throwable {
  final String[] strings;

  IllegalCSVStructureException() {
    this.strings = null;
  }

  IllegalCSVStructureException(String[] strings) {
    this.strings = strings;
  }
}
