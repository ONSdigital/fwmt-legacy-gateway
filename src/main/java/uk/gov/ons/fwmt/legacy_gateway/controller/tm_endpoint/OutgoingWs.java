package uk.gov.ons.fwmt.legacy_gateway.controller.tm_endpoint;

import com.consiliumtechnologies.schemas.mobile._2008._10.locationmessages.SubmitLocationRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.commonmessages.SubmitDocumentRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.CompleteVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.RequestVisitRequest;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitsmessages.UpdateVisitStatusRequest;
import com.consiliumtechnologies.schemas.mobile._2009._07.formsmessages.SubmitFormResultRequest;
import com.consiliumtechnologies.schemas.mobile._2009._09.compositemessages.CompositeVisitRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.bind.JAXBElement;

@Slf4j
@SuppressWarnings("unused")
@Endpoint
public class OutgoingWs {
    private static final String NAMESPACE_URI = "http://schemas.consiliumtechnologies.com/services/mobile/2009/03/messaging";

    public static boolean printDebugging = true;

    @Autowired
    public OutgoingWs() {
    }

    private void stub(String messageType) {
        if (printDebugging) {
            // temp implementation
            log.debug("OutgoingWs : Found message type > " + messageType);
        }
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateVisitStatusRequest")
    @ResponsePayload
    public JAXBElement<UpdateVisitStatusRequest> sendUpdateVisitStatusRequestOutput(@RequestPayload JAXBElement<UpdateVisitStatusRequest> request) {
        stub("SendUpdateVisitStatusRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "completeVisitRequest")
    @ResponsePayload
    // TODO find a way to do this without returning the input
    // http://forum.spring.io/forum/spring-projects/web-services/42740-responding-with-an-empty-soap-body
    public JAXBElement<CompleteVisitRequest> sendCompleteVisitStatusRequestOutputLowercase(@RequestPayload JAXBElement<CompleteVisitRequest> request) {
        stub("SendCompleteVisitStatusRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "requestVisitRequest")
    @ResponsePayload
    public JAXBElement<RequestVisitRequest> sendRequestVisitRequestOutput(@RequestPayload JAXBElement<RequestVisitRequest> request) {
        stub("SendRequestVisitRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitDocumentRequest")
    @ResponsePayload
    public JAXBElement<SubmitDocumentRequest> sendSubmitDocumentRequestOutput(@RequestPayload JAXBElement<SubmitDocumentRequest> request) {
        stub("SendSubmitDocumentRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "compositeVisitRequest")
    @ResponsePayload
    public JAXBElement<CompositeVisitRequest> sendCompositeVisitRequestOutput(@RequestPayload JAXBElement<CompositeVisitRequest> request) {
        stub("SendCompositeVisitRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitFormResultRequest")
    @ResponsePayload
    public JAXBElement<SubmitFormResultRequest> sendSubmitFormResultRequestOutput(@RequestPayload JAXBElement<SubmitFormResultRequest> request) {
        stub("SendSubmitFormResultRequestOutput");
        request.setValue(null);
        return request;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "submitLocationRequest")
    @ResponsePayload
    public JAXBElement<SubmitLocationRequest> sendSubmitLocationRequestOutput(@RequestPayload JAXBElement<SubmitLocationRequest> request) {
        stub("SendSubmitLocationRequestOutput");
        request.setValue(null);
        return request;
    }
}
