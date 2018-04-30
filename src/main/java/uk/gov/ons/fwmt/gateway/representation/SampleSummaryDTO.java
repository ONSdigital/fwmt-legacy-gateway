package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SampleSummaryDTO {
  @Data
  @AllArgsConstructor
  public static class UnprocessedEntry {
    private final String[] content;
    private final int line;
  }

  private final String filename;
  private final int rows;
  private final UnprocessedEntry[] unprocessed;

}
