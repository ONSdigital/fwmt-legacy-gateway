package uk.gov.ons.fwmt.legacy_gateway.service;

import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;

public interface LegacyJobPublishService {
  void publishJob(LegacySampleIngest job);
}
