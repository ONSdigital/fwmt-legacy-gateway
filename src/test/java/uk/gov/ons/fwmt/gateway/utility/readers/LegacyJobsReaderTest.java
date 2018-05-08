package uk.gov.ons.fwmt.gateway.utility.readers;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobIdentityType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.LocationType;
import org.junit.Test;
import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class LegacyJobsReaderTest {

  @Test
  public void createTmJobAndShouldReturnALegacyJobEntity() {

    final String expectedJobId = "test job";
    final String expectedJobLocation = "test location";

    //Given
    CreateJobRequest createJobRequest = new CreateJobRequest();
    createJobRequest.setHasPendingRelations(Boolean.FALSE);

    JobType jobType = new JobType();
    createJobRequest.setJob(jobType);
    JobIdentityType jobIdentityType = new JobIdentityType();
    jobIdentityType.setReference(expectedJobId);
    LocationType locationType = new LocationType();
    locationType.setReference(expectedJobLocation);
    jobType.setLocation(locationType);
    jobType.setIdentity(jobIdentityType);

    //when
    LegacyJobEntity result = LegacyJobsReader.createJobEntity(createJobRequest);

    //then
    assertEquals(LegacyJobsReader.INITIAL_STATE, result.getState());
    assertEquals(null, result.getErroredTimeStamp());
    assertEquals(null, result.sentTimeStamp);
    assertEquals(null, result.getProcessedTimeStamp());
    assertEquals(null, result.getErroredTimeStamp());
    assertEquals(expectedJobId, result.getTmJobId());
    assertEquals(expectedJobLocation,result.getLegacyJobId());
  }

  @Test
  public void whenAJobIsSentItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity initialJobState = new LegacyJobEntity();
    initialJobState.setState(LegacyJobsReader.INITIAL_STATE);
    String initialimeStamp = LocalDateTime.now().minusDays(1).toString();
    initialJobState.setSentTimeStamp(initialimeStamp);

    //When
    LegacyJobEntity resultJobState = LegacyJobsReader.setStateSent(initialJobState);


    //Then
    assertFalse(resultJobState.sentTimeStamp.equals(initialimeStamp));
    assertEquals(LegacyJobsReader.SENT_STATE,resultJobState.getState());
    //    assertEquals("egg",sentJobState.getSentTimeStamp());

  }

  @Test
  public void whenAJobIsProcessedItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity sentJobState = new LegacyJobEntity();
    sentJobState.setState(LegacyJobsReader.SENT_STATE);
    String sentTimeStamp = LocalDateTime.now().minusDays(1).toString();
    sentJobState.setSentTimeStamp(sentTimeStamp);

    //When
    LegacyJobEntity resultJobState = LegacyJobsReader.setStateProcessed(sentJobState);


    //Then
    assertFalse(resultJobState.sentTimeStamp.equals(sentTimeStamp));
    assertEquals(LegacyJobsReader.PROCESSED_STATE,resultJobState.getState());

  }



  public void whenAJobErrorsItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity sentJobState = new LegacyJobEntity();
    sentJobState.setState(LegacyJobsReader.SENT_STATE);
    String sentTimeStamp = LocalDateTime.now().minusDays(1).toString();
    sentJobState.setSentTimeStamp(sentTimeStamp);

    //When
    LegacyJobEntity resultJobState = LegacyJobsReader.setStateErrored(sentJobState);


    //Then
    assertFalse(resultJobState.sentTimeStamp.equals(sentTimeStamp));
    assertEquals(LegacyJobsReader.ERRORED_STATE,resultJobState.getState());

  }
}