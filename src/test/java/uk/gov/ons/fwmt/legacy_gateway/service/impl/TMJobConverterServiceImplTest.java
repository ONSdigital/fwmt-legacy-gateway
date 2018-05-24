package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyCollectionType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;


import static org.junit.Assert.*;
import static uk.gov.ons.fwmt.legacy_gateway.service.impl.TMJobConverterServiceImpl.JOB_QUEUE;

@RunWith(MockitoJUnitRunner.class)
public class TMJobConverterServiceImplTest {

  @InjectMocks
  TMJobConverterServiceImpl tmJobConverterService;

  @Mock TMJobRepo tmJobRepo;
  @Mock TMUserRepo tmUserRepo;

  @Test
  public void addAdditionalProperty() {
    //Given
    String expectedKey = "testKey";
    String expectedValue = "testValue";
    CreateJobRequest createJobRequest = new CreateJobRequest();
    createJobRequest.setJob(new JobType());
    createJobRequest.getJob().setAdditionalProperties(new AdditionalPropertyCollectionType());
    createJobRequest.getJob().getAdditionalProperties();

    //When
    tmJobConverterService.addAdditionalProperty(createJobRequest, expectedKey, expectedValue);

    //Then
    assertEquals(expectedKey,createJobRequest.getJob().getAdditionalProperties().getAdditionalProperty().get(0).getName());
    assertEquals(expectedValue,createJobRequest.getJob().getAdditionalProperties().getAdditionalProperty().get(0).getValue());
  }

//  @Test
//  public void createJobRequestFromIngest() {
//  }
//
//  @Test
//  public void updateJobHeaderRequestFromIngest() {
//  }

  @Test
  public void makeSendMessageRequestInfo() {
    //Given
    String expectedKey = "testKey";

    //When
    SendMessageRequestInfo result = tmJobConverterService.makeSendMessageRequestInfo(expectedKey);

    //Then
    assertEquals(expectedKey,result.getKey());
    assertEquals(JOB_QUEUE,result.getQueueName());
  }

//  @Test
//  public void reallocateJob() {
//  }
//
//  @Test
//  public void newJob() {
//  }
//
//  @Test
//  public void publishJobToUser() {
//  }
//
//  @Test
//  public void publishJob() {
//  }
}