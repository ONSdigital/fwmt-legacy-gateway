package uk.gov.ons.fwmt.legacy_gateway.controller.tm_endpoint;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.*;
import org.junit.Test;

import javax.xml.bind.JAXBElement;

import static org.junit.Assert.*;

public class MessageQueueWsTest {

  MessageQueueWs messageQueueWs = new MessageQueueWs();

  @Test
  public void sendMessage() {
    //Given

    //When
    JAXBElement<SendMessageResponse> result = messageQueueWs.sendMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void transformAndSendMessage() {
    //Given

    //When
    JAXBElement<TransformAndSendResponse> result = messageQueueWs.transformAndSendMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void query() {
    //Given

    //When
    JAXBElement<QueryMessagesResponse> result = messageQueueWs.query(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void get() {
    //Given

    //When
    JAXBElement<GetMessageResponse> result = messageQueueWs.get(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void delete() {
    //Given

    //When
    JAXBElement<DeleteMessageResponse> result = messageQueueWs.delete(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void retry() {
    //Given

    //When
    JAXBElement<RetryMessageResponse> result = messageQueueWs.retry(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void reset() {
    //Given

    //When
    JAXBElement<ResetMessageResponse> result = messageQueueWs.reset(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreateVisitRequestMessage() {
    //Given

    //When
    SendCreateVisitRequestMessageResponse result = messageQueueWs.sendCreateVisitRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendForceRecallVisitRequestMessage() {
    //Given

    //When
    SendForceRecallVisitRequestMessageResponse result = messageQueueWs.sendForceRecallVisitRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendAddVisitTasksRequestMessage() {
    //Given

    //When
    SendAddVisitTasksRequestMessageResponse result = messageQueueWs.sendAddVisitTasksRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendUpdateVisitScheduleRequestMessage() {
    //Given

    //When
    SendUpdateVisitScheduleRequestMessageResponse result = messageQueueWs.sendUpdateVisitScheduleRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendUpdateVisitHeaderRequestMessage() {
    //Given

    //When
    SendUpdateVisitHeaderRequestMessageResponse result = messageQueueWs.sendUpdateVisitHeaderRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreateBulletinRequestMessage() {
    //Given

    //When
    SendCreateBulletinRequestMessageResponse result = messageQueueWs.sendCreateBulletinRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendDeleteBulletinRequestMessage() {
    //Given

    //When
    SendDeleteBulletinRequestMessageResponse result = messageQueueWs.sendDeleteBulletinRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendGenerateFolioContentRequestMessage() {
    //Given

    //When
    SendGenerateFolioContentRequestMessageResponse result = messageQueueWs.sendGenerateFolioContentRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendAddFolioContentRequestMessage() {
    //Given

    //When
    SendAddFolioContentRequestMessageResponse result = messageQueueWs.sendAddFolioContentRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreateReferralRequestMessage() {
    //Given

    //When
    SendCreateReferralRequestMessageResponse result = messageQueueWs.sendCreateReferralRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreatePatientRequestMessage() {
    //Given

    //When
    SendCreatePatientRequestMessageResponse result = messageQueueWs.sendCreatePatientRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendUpdateReferralRequestMessage() {
    //Given

    //When
    SendUpdateReferralRequestMessageResponse result = messageQueueWs.sendUpdateReferralRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreateAppointmentRequestMessage() {
    //Given

    //When
    SendCreateAppointmentRequestMessageResponse result = messageQueueWs.sendCreateAppointmentRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendDischargeReferralRequestMessage() {
    //Given

    //When
    SendDischargeReferralRequestMessageResponse result = messageQueueWs.sendDischargeReferralRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCreateJobRequestMessage() {
    //Given

    //When
    SendCreateJobRequestMessageResponse result = messageQueueWs.sendCreateJobRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendDeleteJobRequestMessage() {
    //Given

    //When
    SendDeleteJobRequestMessageResponse result = messageQueueWs.sendDeleteJobRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendAddJobTasksRequestMessage() {
    //Given

    //When
    SendAddJobTasksRequestMessageResponse result = messageQueueWs.sendAddJobTasksRequestMessage(null);

    //Then
    assertNotNull(result);

  }

  @Test
  public void sendSaveAvailabilityRequestMessage() {
    //Given

    //When
    SendSaveAvailabilityRequestMessageResponse result = messageQueueWs.sendSaveAvailabilityRequestMessage(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendUpdateJobHeaderRequestMessage() {
    //Given

    //When
    SendUpdateJobHeaderRequestMessageResponse result = messageQueueWs.sendUpdateJobHeaderRequestMessage(null);

    //Then
    assertNotNull(result);
  }
}