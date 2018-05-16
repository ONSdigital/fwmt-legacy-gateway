package uk.gov.ons.fwmt.legacy_gateway.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ManagementController {
  @RequestMapping(value = "/temp", method = RequestMethod.POST, produces = "application/json")
  @ApiResponses(value = {
  })
  public void temp() {

  }
}
