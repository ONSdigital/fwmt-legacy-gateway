package uk.gov.ons.fwmt.gateway.utility.readers;

import java.util.Calendar;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import com.opencsv.bean.CsvToBean;

import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

public class LegacyJobsReader  extends CsvToBean<LegacyJobEntity>{

    public static LegacyJobEntity createJobEntity(CreateJobRequest createJobRequest) {
        LegacyJobEntity legacyJobEntity = new LegacyJobEntity();
        JobType job = createJobRequest.getJob();
        
        legacyJobEntity.setTmjobid(job.getIdentity().getReference());
        legacyJobEntity.setLegacyjobid(job.getLocation().getReference());
        legacyJobEntity.setState("INITAL");
        legacyJobEntity.setInitaltimestamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
        legacyJobEntity.setSenttimestamp(null);
        legacyJobEntity.setProcessedtimestamp(null);
        legacyJobEntity.setErroredtimestamp(null);
        
        return legacyJobEntity;
    }

    public static LegacyJobEntity setStateSent(LegacyJobEntity jobEntity) {
        jobEntity.setState("SENT");
        jobEntity.setSenttimestamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
        return jobEntity;
    }

    public static LegacyJobEntity setStateProcessed(LegacyJobEntity jobEntity) {
        jobEntity.setState("PROCESSED");
        jobEntity.setSenttimestamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
        return jobEntity;
    }
    
    public static LegacyJobEntity setStateErrored(LegacyJobEntity jobEntity) {
        jobEntity.setState("ERRORED");
        jobEntity.setSenttimestamp(new java.sql.Date(Calendar.getInstance().getTime().getTime()).toString());
        return jobEntity;
    }
}
