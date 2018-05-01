package uk.gov.ons.fwmt.gateway.error;

import lombok.Data;

@Data
public class GatewayCommonErrorDTO {
  String error;
  String exception;
  String message;
  String path;
  int status;
  String timestamp;
}
