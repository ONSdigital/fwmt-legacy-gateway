package uk.gov.ons.fwmt.legacy_gateway.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.gov.ons.fwmt.legacy_gateway.data.csv_parser.UnprocessedCSVRow;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleSummaryDTO {
  private final String filename;
  private final int processedRows;
  private final List<UnprocessedCSVRow> unprocessedRows;

}
