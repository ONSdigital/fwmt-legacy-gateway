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
    LegacyJobEntity result = new LegacyJobEntity(createJobRequest);

    //then
    assertEquals(LegacyJobEntity.INITIAL_STATE, result.getState());
    assertEquals(null, result.getErroredTimeStamp());
    assertEquals(null, result.sentTimeStamp);
    assertEquals(null, result.getProcessedTimeStamp());
    assertEquals(null, result.getErroredTimeStamp());
    assertEquals(expectedJobId, result.getTmJobId());
    assertEquals(expectedJobLocation, result.getLegacyJobId());
  }

  @Test
  public void whenAJobIsSentItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity initialJobState = new LegacyJobEntity();
    initialJobState.setState(LegacyJobEntity.INITIAL_STATE);
    String initialimeStamp = LocalDateTime.now().minusDays(1).toString();
    initialJobState.setSentTimeStamp(initialimeStamp);

    //When
    initialJobState.setStateSent();

    //Then
    assertFalse(initialJobState.sentTimeStamp.equals(initialimeStamp));
    assertEquals(LegacyJobEntity.SENT_STATE, initialJobState.getState());
    //    assertEquals("egg",sentJobState.getSentTimeStamp());

  }

  @Test
  public void whenAJobIsProcessedItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity sentJobState = new LegacyJobEntity();
    sentJobState.setState(LegacyJobEntity.SENT_STATE);
    String sentTimeStamp = LocalDateTime.now().minusDays(1).toString();
    sentJobState.setSentTimeStamp(sentTimeStamp);

    //When
    sentJobState.setStateProcessed();

    //Then
    assertFalse(sentJobState.sentTimeStamp.equals(sentTimeStamp));
    assertEquals(LegacyJobEntity.PROCESSED_STATE, sentJobState.getState());

  }

  public void whenAJobErrorsItsStateAndtimeStampShouldBeUpdated() {

    //Given
    LegacyJobEntity sentJobState = new LegacyJobEntity();
    sentJobState.setState(LegacyJobEntity.SENT_STATE);
    String sentTimeStamp = LocalDateTime.now().minusDays(1).toString();
    sentJobState.setSentTimeStamp(sentTimeStamp);

    //When
    sentJobState.setStateErrored();

    //Then
    assertFalse(sentJobState.sentTimeStamp.equals(sentTimeStamp));
    assertEquals(LegacyJobEntity.ERRORED_STATE, sentJobState.getState());

  }
}