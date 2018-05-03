package uk.gov.ons.fwmt.gateway.error;

import lombok.Data;

@Data
public class GatewayCommonErrorDTO {
  public String error;
  public String exception;
  public String message;
  public String path;
  public int status;
  public String timestamp;
}
