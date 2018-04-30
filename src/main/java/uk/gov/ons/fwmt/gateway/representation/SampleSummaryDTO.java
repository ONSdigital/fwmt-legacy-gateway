package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SampleSummaryDTO {
  @Data
  @AllArgsConstructor
  public static class UnprocessedEntry {
    private final String[] content;
    private final int row;
    private final String message;
  }

  private final String filename;
  private final int processedRows;
  private final List<UnprocessedEntry> unprocessedRows;

}
