package uk.gov.ons.fwmt.gateway.entity.csv_parser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnprocessedCSVRow {
  private final int row;
  private final String message;
}
