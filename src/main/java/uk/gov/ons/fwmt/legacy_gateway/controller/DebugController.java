/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.legacy_gateway.controller;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.data.tm.UserForm;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.TMJobConverterService;
import uk.gov.ons.fwmt.legacy_gateway.service.TMService;
import uk.gov.ons.fwmt.legacy_gateway.service.TMWebDriver;

import java.io.IOException;

/**
 * A class for manually triggering various debugging actions
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@RestController
@Profile({"dev", "test"})
@RequestMapping("/debug")
public class DebugController {
  private TMUserRepo tmUserRepo;

  private TMJobConverterService tmJobConverterService;
  private TMService tmService;
  private TMWebDriver tmWebDriver;

  @Autowired
  public DebugController(
      TMUserRepo tmUserRepo,
      TMJobConverterService tmJobConverterService,
      TMService tmService,
      TMWebDriver tmWebDriver) {
    this.tmUserRepo = tmUserRepo;
    this.tmJobConverterService = tmJobConverterService;
    this.tmService = tmService;
    this.tmWebDriver = tmWebDriver;
  }

  @PostMapping
  public void clearUsers() {
    tmUserRepo.deleteAll();
  }

  @PostMapping
  public void newTMUser(UserForm form) throws IOException {
    tmWebDriver.makeNewUser(form);
  }

  @PostMapping
  public ResponseEntity<SendMessageResponse> newJob(LegacySampleIngest ingest) {
    String username = tmUserRepo.findByAuthNo(ingest.getAuth()).getTmUsername();
    CreateJobRequest request = tmJobConverterService.createJob(ingest, username);
    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    message.setCreateJobRequest(request);
    SendCreateJobRequestMessageResponse response = tmService.send(message);
    return new ResponseEntity<>(response.getSendMessageResponse(), new HttpHeaders(), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<SendMessageResponse> reallocateJob(String jobId, String targetAuthNo) {
    String username = tmUserRepo.findByAuthNo(targetAuthNo).getTmUsername();
    UpdateJobHeaderRequest request = tmJobConverterService.updateJob(jobId, username);
    SendUpdateJobHeaderRequestMessage message = new SendUpdateJobHeaderRequestMessage();
    message.setUpdateJobHeaderRequest(request);
    SendUpdateJobHeaderRequestMessageResponse response = tmService.send(message);
    return new ResponseEntity<>(response.getSendMessageResponse(), new HttpHeaders(), HttpStatus.OK);
  }
}
