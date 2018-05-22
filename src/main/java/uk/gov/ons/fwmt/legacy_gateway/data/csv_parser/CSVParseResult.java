package uk.gov.ons.fwmt.legacy_gateway.data.csv_parser;

import lombok.Getter;

/**
 * This class captures the potential for the CSV parser to return errors for the caller to handle
 *
 * @param <T> The data object that is to be produced when CSV parsing succeeds
 */
public class CSVParseResult<T> {
  @Getter private final int row;
  @Getter private final T result;
  @Getter private final String errorMessage;

  private CSVParseResult(int row, T result) {
    this.row = row;
    this.result = result;
    this.errorMessage = null;
  }

  private CSVParseResult(int row, String errorMessage) {
    this.row = row;
    this.result = null;
    this.errorMessage = errorMessage;
  }

  // Static constructors for clearer display of intent
  public static <T> CSVParseResult<T> withError(int row, String errorMessage) {
    return new CSVParseResult<>(row, errorMessage);
  }

  // Static constructors for clearer display of intent
  public static <T> CSVParseResult<T> withResult(int row, T result) {
    return new CSVParseResult<>(row, result);
  }

  public boolean isError() {
    return errorMessage != null;
  }

  public boolean isResult() {
    return result != null;
  }
}
