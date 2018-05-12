package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.legacy.SampleIngest;

@Service
public class LegacyJobPublishService {
  private final TMService tmService;

  public LegacyJobPublishService(TMService tmService) {
    this.tmService = tmService;
  }

  public void publishJob(SampleIngest job) {
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

  private void reissueJob(SampleIngest job) {
    // TODO
  }

  private void reallocateJob(SampleIngest job) {
    // TODO
  }

  private void createJob(SampleIngest job) {
    // TODO
  }
}

