package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UnprocessedCSVRowDTO {
  private final List<String> content;
  private final int row;
  private final String message;
}
