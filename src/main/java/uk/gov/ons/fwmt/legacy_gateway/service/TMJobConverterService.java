package uk.gov.ons.fwmt.legacy_gateway.service;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;

public interface TMJobConverterService {
  CreateJobRequest createNewJob(LegacySampleIngest ingest, String username);
  UpdateJobHeaderRequest createReallocation(LegacySampleIngest ingest, String username);
  CreateJobRequest createReissue(LegacySampleIngest ingest, String username);
}

