package uk.gov.ons.fwmt.legacy_gateway.controller.tm_endpoint;

import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.CompleteVisitRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.WebServiceAdapterOutputRequest;
import org.junit.Test;

import javax.xml.bind.JAXBElement;

import static org.junit.Assert.*;

public class GenericOutgoingWsTest {

  GenericOutgoingWs genericOutgoingWs = new GenericOutgoingWs();

  @Test
  public void sendAdapterOutput() {
    //Given


    //When
    JAXBElement<SendMessageResponse> result = genericOutgoingWs.sendAdapterOutput(null);

    //Then
    assertNotNull(result);
  }
}