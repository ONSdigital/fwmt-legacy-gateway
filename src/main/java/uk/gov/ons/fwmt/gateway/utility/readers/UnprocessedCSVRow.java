package uk.gov.ons.fwmt.gateway.utility.readers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnprocessedCSVRow {
  private final String[] content;
  private final int row;
  private final String message;
}
