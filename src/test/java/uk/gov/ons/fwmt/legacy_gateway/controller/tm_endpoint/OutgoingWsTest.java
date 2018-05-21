package uk.gov.ons.fwmt.legacy_gateway.controller.tm_endpoint;

import com.consiliumtechnologies.schemas.mobile._2008._10.locationmessages.SubmitLocationRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.commonmessages.SubmitDocumentRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.CompleteVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.RequestVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitStatusRequest;
import com.consiliumtechnologies.schemas.mobile._2009._07.formsmessages.SubmitFormResultRequest;
import com.consiliumtechnologies.schemas.mobile._2009._09.compositemessages.CompositeVisitRequest;
import org.junit.Test;

import javax.xml.bind.JAXBElement;

import static org.junit.Assert.*;

public class OutgoingWsTest {

  OutgoingWs outgoingWs = new OutgoingWs();

  @Test
  public void sendUpdateVisitStatusRequestOutput() {
    //Given

    //When
    JAXBElement<UpdateVisitStatusRequest> result = outgoingWs.sendUpdateVisitStatusRequestOutput(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCompleteVisitStatusRequestOutputLowercase() {
    //Given

    //When
    JAXBElement<CompleteVisitRequest> result = outgoingWs.sendCompleteVisitStatusRequestOutputLowercase(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendRequestVisitRequestOutput() {
    //Given

    //When
    JAXBElement<RequestVisitRequest> result = outgoingWs.sendRequestVisitRequestOutput(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendSubmitDocumentRequestOutput() {
    //Given

    //When
    JAXBElement<SubmitDocumentRequest> result = outgoingWs.sendSubmitDocumentRequestOutput(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendCompositeVisitRequestOutput() {
    //Given

    //When
    JAXBElement<CompositeVisitRequest> result = outgoingWs.sendCompositeVisitRequestOutput(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendSubmitFormResultRequestOutput() {
    //Given

    //When
    JAXBElement<SubmitFormResultRequest> result = outgoingWs.sendSubmitFormResultRequestOutput(null);

    //Then
    assertNotNull(result);
  }

  @Test
  public void sendSubmitLocationRequestOutput() {
    //Given

    //When
    JAXBElement<SubmitLocationRequest> result = outgoingWs.sendSubmitLocationRequestOutput(null);

    //Then
    assertNotNull(result);
  }
}