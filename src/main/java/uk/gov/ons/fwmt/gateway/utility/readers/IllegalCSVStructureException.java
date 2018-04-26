package uk.gov.ons.fwmt.gateway.utility.readers;


public class IllegalCSVStructureException extends Throwable {
  private static final long serialVersionUID = 1402367242522785524L;

  final String[] strings;

  IllegalCSVStructureException() {
    this.strings = null;
  }

  IllegalCSVStructureException(String[] strings) {
    this.strings = strings;
  }
}
