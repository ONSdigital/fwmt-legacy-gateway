package uk.gov.ons.fwmt.legacy_gateway.service;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.*;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This service handles all of the interactions between this legacy_gateway and TotalMobile
 * This interaction largely consists of sending messages in SOAP format
 */
@Service
public class TMService extends WebServiceGatewaySupport {
  // A lookup detailing the instances where the message name does not translate easily into a SOAP action
  // Normally, we assume that the SOAP action is equal to the class name with the word 'Response' at the end removed
  private static final Map<Class<?>, String> messageActionMap;

  // A list of all classes denoting valid TM messages
  private static Class<?>[] knownMessageNames = {
      SendMessageRequest.class, SendMessageResponse.class,
      TransformAndSendRequest.class, TransformAndSendResponse.class,
      QueryMessagesRequest.class, QueryMessagesResponse.class,
      GetMessageRequest.class, GetMessageResponse.class,
      DeleteMessageRequest.class, DeleteMessageResponse.class,
      RetryMessageRequest.class, RetryMessageResponse.class,
      ResetMessageRequest.class, ResetMessageResponse.class,
      SendCreateVisitRequestMessage.class, SendCreateVisitRequestMessageResponse.class,
      SendForceRecallVisitRequestMessage.class, SendForceRecallVisitRequestMessageResponse.class,
      SendAddVisitTasksRequestMessage.class, SendAddVisitTasksRequestMessageResponse.class,
      SendUpdateVisitScheduleRequestMessage.class, SendUpdateVisitScheduleRequestMessageResponse.class,
      SendUpdateVisitHeaderRequestMessage.class, SendUpdateVisitHeaderRequestMessageResponse.class,
      SendCreateBulletinRequestMessage.class, SendCreateBulletinRequestMessageResponse.class,
      SendDeleteBulletinRequestMessage.class, SendDeleteBulletinRequestMessageResponse.class,
      SendGenerateFolioContentRequestMessage.class, SendGenerateFolioContentRequestMessageResponse.class,
      SendAddFolioContentRequestMessage.class, SendAddFolioContentRequestMessageResponse.class,
      SendCreateReferralRequestMessage.class, SendCreateReferralRequestMessageResponse.class,
      SendCreatePatientRequestMessage.class, SendCreatePatientRequestMessageResponse.class,
      SendUpdateReferralRequestMessage.class, SendUpdateReferralRequestMessageResponse.class,
      SendCreateAppointmentRequestMessage.class, SendCreateAppointmentRequestMessageResponse.class,
      SendDischargeReferralRequestMessage.class, SendDischargeReferralRequestMessageResponse.class,
      SendCreateJobRequestMessage.class, SendCreateJobRequestMessageResponse.class,
      SendDeleteJobRequestMessage.class, SendDeleteJobRequestMessageResponse.class,
      SendAddJobTasksRequestMessage.class, SendAddJobTasksRequestMessageResponse.class,
      SendSaveAvailabilityRequestMessage.class, SendSaveAvailabilityRequestMessageResponse.class,
      SendUpdateJobHeaderRequestMessage.class, SendUpdateJobHeaderRequestMessageResponse.class,
  };

  static {
    messageActionMap = new HashMap<>();
    messageActionMap.put(SendMessageRequest.class, "SendMessage");
    messageActionMap.put(SendMessageResponse.class, "SendMessage");
    messageActionMap.put(TransformAndSendRequest.class, "TransformAndSendMessage");
    messageActionMap.put(TransformAndSendResponse.class, "TransformAndSendMessage");
    messageActionMap.put(QueryMessagesRequest.class, "Query");
    messageActionMap.put(QueryMessagesResponse.class, "Query");
    messageActionMap.put(GetMessageRequest.class, "Get");
    messageActionMap.put(GetMessageResponse.class, "Get");
    messageActionMap.put(DeleteMessageRequest.class, "Delete");
    messageActionMap.put(DeleteMessageResponse.class, "Delete");
    messageActionMap.put(RetryMessageRequest.class, "Retry");
    messageActionMap.put(RetryMessageResponse.class, "Retry");
    messageActionMap.put(ResetMessageRequest.class, "Reset");
    messageActionMap.put(ResetMessageResponse.class, "Reset");
  }

  private final String messageQueueUrl;
  private final String namespace;

  @Autowired
  public TMService(
      @Value("${totalmobile.url}") String url,
      @Value("${totalmobile.message-queue-path}") String messageQueuePath,
      @Value("${totalmobile.message-queue-wsdl-path}") String messageQueueWSDLPath,
      @Value("${totalmobile.namespace}") String namespace,
      @Value("${totalmobile.username}") String username,
      @Value("${totalmobile.password}") String password) {
    this.messageQueueUrl = url + messageQueuePath;
    this.namespace = namespace;
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath(messageQueueWSDLPath);
    this.setMarshaller(marshaller);
    HttpComponentsMessageSender messageSender = new HttpComponentsMessageSender();
    messageSender.setCredentials(new UsernamePasswordCredentials(username, password));
    this.setMessageSender(messageSender);
  }

  /**
   * Translates a class name into the SOAP action expected by TotalMobile
   * <p>
   * If we have a specific mapping, we use such
   * If we do not, we use the general case of removing the word 'Response'
   */
  private String lookupSOAPAction(Class<?> cl) {
    if (!Arrays.asList(knownMessageNames).contains(cl)) {
      throw new IllegalArgumentException("Message passed that does not match any TotalMobile message");
    }
    String className = cl.getSimpleName();
    if (messageActionMap.containsKey(cl)) {
      return namespace + messageActionMap.get(cl);
    } else {
      return namespace + className.replace("Response", "");
    }
  }

  public <I, O> O send(I message) {
    String soapAction = lookupSOAPAction(message.getClass());
    @SuppressWarnings("unchecked")
    O response = (O) getWebServiceTemplate()
        .marshalSendAndReceive(messageQueueUrl, message, new SoapActionCallback(soapAction));
    if (!Arrays.asList(knownMessageNames).contains(response.getClass())) {
      throw new IllegalArgumentException("Message received from TM that does not match any TotalMobile message");
    }
    return response;
  }

}
