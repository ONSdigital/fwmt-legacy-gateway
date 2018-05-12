package uk.gov.ons.fwmt.legacy_gateway.data.csv_parser;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CSVParseResult {
  private final List<UnprocessedCSVRow> unprocessedCSVRows;
  private final int parsedCount;
  private final int unparsedCount;
}
