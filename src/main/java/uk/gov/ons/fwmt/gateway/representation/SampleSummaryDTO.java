package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleSummaryDTO {
  private final String filename;
  private final int processedRows;
  private final List<UnprocessedCSVRowDTO> unprocessedRows;

}
