package uk.gov.ons.fwmt.gateway.tm.endpoint;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.WebServiceAdapterOutputRequest;
import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.controller.LegacyEndpointRESTController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.JAXBElement;

@Slf4j
@SuppressWarnings("unused")
@Endpoint

public class GenericOutgoingWs {
    private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

    public static boolean printDebugging = true;

    @Autowired
    public GenericOutgoingWs() {
    }

    private void stub(String messageType) {
        if (printDebugging) {
            // temp implementation
            log.debug("Found message type > " + messageType);
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "request")
    @ResponsePayload
    public JAXBElement<SendMessageResponse> sendAdapterOutput(@RequestPayload JAXBElement<WebServiceAdapterOutputRequest> completeVisitRequest) {
        stub("SendAdapterOutput");
        SendMessageResponse response = new SendMessageResponse();
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createSendMessageResponse(response);
    }
}
