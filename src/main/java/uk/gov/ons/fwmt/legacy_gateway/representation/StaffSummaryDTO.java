package uk.gov.ons.fwmt.legacy_gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffSummaryDTO {

  private String filename;
  private int rows;

}
