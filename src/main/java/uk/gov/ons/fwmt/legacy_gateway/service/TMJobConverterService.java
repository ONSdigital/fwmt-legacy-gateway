package uk.gov.ons.fwmt.legacy_gateway.service;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.DeleteJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;

public interface TMJobConverterService {
  CreateJobRequest createJob(LegacySampleIngest ingest, String username);
  UpdateJobHeaderRequest updateJob(String tmJobId, String username);
  UpdateJobHeaderRequest updateJob(LegacySampleIngest ingest, String username);
  CreateJobRequest createReissue(LegacySampleIngest ingest, String username);
  DeleteJobRequest deleteJob(String tmJobId);
}

