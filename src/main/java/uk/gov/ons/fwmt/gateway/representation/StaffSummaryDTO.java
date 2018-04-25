package uk.gov.ons.fwmt.gateway.representation;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaffSummaryDTO {

  private String filename;
  private int rows;

}
