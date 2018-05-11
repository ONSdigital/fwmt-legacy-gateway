package uk.gov.ons.fwmt.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import uk.gov.ons.fwmt.gateway.entity.legacy.JobIngest;
import uk.gov.ons.fwmt.gateway.service.DBService;
import uk.gov.ons.fwmt.gateway.service.LegacyJobPublishService;
import uk.gov.ons.fwmt.gateway.service.TMService;

public class LegacyJobPublishServiceImpl implements LegacyJobPublishService {
  private final DBService dbService;
  private final TMService tmService;

  @Autowired
  public LegacyJobPublishServiceImpl(DBService dbService, TMService tmService) {
    this.dbService = dbService;
    this.tmService = tmService;
  }

  @Override
  public void publishJob(JobIngest job) {
    // TODO
    // Logic:
    // If jobid does not exist in database:
    //   if survey = gff:
    //     jobid is quota-addressNo-FP
    //     if substr(FP, 1, 3) > 12:
    //       reissue with correct due date
    //     else:
    //       new case with correct due date
    //   else if survey = lfs:
    //     jobid is quota-week-w1yr-qrtr-addressNo-wavfnd-hhld-chklet-FP
    //     calculate due date with to be decided lookup/calculation
    //     TODO is this a new job?
    //   else:
    //     die
    // else if jobid exists:
    //   reallocation, update the user
  }

  private void reissueJob(JobIngest job) {
    // TODO
  }

  private void reallocateJob(JobIngest job) {
    // TODO
  }

  private void createJob(JobIngest job) {
    // TODO
  }
}

