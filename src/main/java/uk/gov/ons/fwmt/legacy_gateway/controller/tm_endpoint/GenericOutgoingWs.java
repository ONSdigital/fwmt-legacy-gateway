package uk.gov.ons.fwmt.legacy_gateway.controller.tm_endpoint;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.WebServiceAdapterOutputRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Slf4j
@Endpoint
public class GenericOutgoingWs {
  private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

  private void stub(String messageType) {
    log.debug("Found message type > " + messageType);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = "request")
  @ResponsePayload
  public JAXBElement<SendMessageResponse> sendAdapterOutput(
      @RequestPayload JAXBElement<WebServiceAdapterOutputRequest> completeVisitRequest) {
    stub("SendAdapterOutput");
    SendMessageResponse response = new SendMessageResponse();
    ObjectFactory objectFactory = new ObjectFactory();
    return objectFactory.createSendMessageResponse(response);
  }
}
