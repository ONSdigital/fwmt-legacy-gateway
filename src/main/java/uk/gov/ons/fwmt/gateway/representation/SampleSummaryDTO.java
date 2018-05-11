package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;
import uk.gov.ons.fwmt.gateway.entity.internal.csv.UnprocessedCSVRow;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleSummaryDTO {
  private final String filename;
  private final int processedRows;
  private final List<UnprocessedCSVRow> unprocessedRows;

}
