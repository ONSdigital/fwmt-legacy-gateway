package uk.gov.ons.fwmt.gateway.service;

import uk.gov.ons.fwmt.gateway.entity.legacy.JobIngest;

public interface LegacyJobPublishService {

  void publishJob(JobIngest job);


}
