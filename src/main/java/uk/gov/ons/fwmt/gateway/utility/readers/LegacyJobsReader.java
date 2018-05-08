package uk.gov.ons.fwmt.gateway.utility.readers;

import java.time.LocalDateTime;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import com.opencsv.bean.CsvToBean;

import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

public class LegacyJobsReader  extends CsvToBean<LegacyJobEntity>{

  public static final String INITIAL_STATE = "INITIAL";
  public static final String SENT_STATE = "SENT";
  public static final String PROCESSED_STATE = "PROCESSED";
  public static final String ERRORED_STATE = "ERROR";


  public static LegacyJobEntity createJobEntity(CreateJobRequest createJobRequest) {
    LegacyJobEntity legacyJobEntity = new LegacyJobEntity();
    JobType job = createJobRequest.getJob();

    legacyJobEntity.setTmJobId(job.getIdentity().getReference());
    legacyJobEntity.setLegacyJobId(job.getLocation().getReference());
    legacyJobEntity.setState(INITIAL_STATE);
    legacyJobEntity.setInitialTimeStamp(LocalDateTime.now().toString());
    legacyJobEntity.setSentTimeStamp(null);
    legacyJobEntity.setProcessedTimeStamp(null);
    legacyJobEntity.setErroredTimeStamp(null);

    return legacyJobEntity;
  }

  public static LegacyJobEntity setStateSent(LegacyJobEntity jobEntity) {
    jobEntity.setState(SENT_STATE);
    jobEntity.setSentTimeStamp(LocalDateTime.now().toString());
    return jobEntity;
  }

  public static LegacyJobEntity setStateProcessed(LegacyJobEntity jobEntity) {
    jobEntity.setState(PROCESSED_STATE);
    jobEntity.setSentTimeStamp(LocalDateTime.now().toString());
    return jobEntity;
  }

  public static LegacyJobEntity setStateErrored(LegacyJobEntity jobEntity) {
    jobEntity.setState(ERRORED_STATE);
    jobEntity.setSentTimeStamp(LocalDateTime.now().toString());
    return jobEntity;
  }
}
