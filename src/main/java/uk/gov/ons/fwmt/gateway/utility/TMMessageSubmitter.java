/*
 * Copyright.. etc
 */

package uk.gov.ons.fwmt.gateway.utility;

import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import java.io.IOException;
import java.util.*;

/**
 * A utility class that handles the process of sending a message of a specific
 * type to Total Mobile
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */
@Component
public class TMMessageSubmitter {
  private static final boolean debug_on = true;

  // this is to avoid needing the terrible case statement in lookupAction
  private static final Map<Class, String> oddMessageNames;
  private static String namespace = "http://schemas.consiliumtechnologies.com/wsdl/mobile/2007/07/messaging/";
  private static Class[] knownMessageNames = {
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
    Map<Class, String> messageNames = new HashMap<>();
    messageNames.put(SendMessageRequest.class, "SendMessage");
    messageNames.put(SendMessageResponse.class, "SendMessage");
    messageNames.put(TransformAndSendRequest.class, "TransformAndSendMessage");
    messageNames.put(TransformAndSendResponse.class, "TransformAndSendMessage");
    messageNames.put(QueryMessagesRequest.class, "Query");
    messageNames.put(QueryMessagesResponse.class, "Query");
    messageNames.put(GetMessageRequest.class, "Get");
    messageNames.put(GetMessageResponse.class, "Get");
    messageNames.put(DeleteMessageRequest.class, "Delete");
    messageNames.put(DeleteMessageResponse.class, "Delete");
    messageNames.put(RetryMessageRequest.class, "Retry");
    messageNames.put(RetryMessageResponse.class, "Retry");
    messageNames.put(ResetMessageRequest.class, "Reset");
    messageNames.put(ResetMessageResponse.class, "Reset");
    oddMessageNames = messageNames;
  }

  @Value("${totalmobile.url}")
  private String urlStart;
  @Value("${totalmobile.message-queue-service}")
  private String urlEnd;
  @Value("${totalmobile.username}")
  private String username;
  @Value("${totalmobile.password}")
  private String password;

  /**
   * Takes a visit request in order to return a complete XML document from the
   * CSV
   *
   * @param message Update request message
   * @return The path used
   */
  private String lookupAction(Object message) {
    if (message instanceof JAXBElement) {
      message = ((JAXBElement) message).getValue();
    }
    final Class cl = message.getClass();
    if (Arrays.stream(knownMessageNames).noneMatch(c -> c == cl)) {
      throw new IllegalArgumentException(
          "Invalid arguments - message's class did not match a known TotalMobile message");
      // throw new IllegalArgumentException("Invalid arguments - message's class
      // did not match a known TotalMobile message: " + classEnd);
    }
    String tail;
    String fullClass = message.getClass().getName();
    int lastDotIndex = fullClass.lastIndexOf('.');
    String classEnd = fullClass.substring(lastDotIndex == -1 ? 0 : lastDotIndex + 1);
    if (oddMessageNames.containsKey(message.getClass())) {
      // The exceptionary case is detailed within oddMessageNames
      return namespace + oddMessageNames.get(message.getClass());
    } else {
      // The common case is that RequestMessage and RequestMessageResponse be
      // mapped to RequestMessage
      return namespace + classEnd.replace("Response", "");
    }
  }

  /**
   * Takes a visit request in order to return a complete XML document from the
   * CSV
   *
   * @param request Update request message
   * @return converted XML file
   * @throws javax.xml.bind.JAXBException
   * @throws javax.xml.parsers.ParserConfigurationException
   */
  private <I> Document toDocument(I request)
      throws javax.xml.bind.JAXBException, javax.xml.parsers.ParserConfigurationException {
    // Create the JAXBContext
    JAXBContext jc = JAXBContext.newInstance(ObjectFactory.class);

    // Create the Document
    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
    DocumentBuilder db = dbf.newDocumentBuilder();
    Document document = db.newDocument();

    // Marshal the Object to a Document
    Marshaller marshaller = jc.createMarshaller();
    marshaller.marshal(request, document);

    return document;
  }

  /**
   * Converts the XML document into a SOAPmessage that can be used for
   * webservice
   *
   * @param document XML file to be converted into a SOAP message
   * @param action The SOAPAction header to send with the message
   * @return SOAP message to sent to webservice
   * @throws javax.xml.soap.SOAPException
   * @throws java.io.IOException
   */
  private SOAPMessage soapConstruct(Document document, String action)
      throws javax.xml.soap.SOAPException, java.io.IOException {
    MessageFactory messageFactory = MessageFactory.newInstance();
    SOAPMessage soapMessage = messageFactory.createMessage();

    SOAPPart soapPart = soapMessage.getSOAPPart();

    // SOAP Envelope
    SOAPEnvelope envelope = soapPart.getEnvelope();
    // envelope.addNamespaceDeclaration(myNamespace, myNamespaceURI);

    // SOAP Body
    SOAPBody soapBody = envelope.getBody();
    soapBody.addDocument(document);

    // SOAP Headers
    MimeHeaders headers = soapMessage.getMimeHeaders();
    headers.setHeader("SOAPAction", action);

    // Finalize
    soapMessage.saveChanges();

    /* Print the request message, just for debugging purposes */
    if (debug_on) {
      System.out.println("Request SOAP Message:");
      soapMessage.writeTo(System.out);
      System.out.println();
    }

    return soapMessage;
  }

  /**
   * @param response Messages received back from the webservice
   * @return message from webservice
   * @throws javax.xml.bind.JAXBException
   * @throws javax.xml.soap.SOAPException
   */
  @SuppressWarnings("unchecked")
  private <O> O soapResponseUnmarshal(SOAPMessage response)
      throws JAXBException, SOAPException {
    Unmarshaller unmarshaller = JAXBContext.newInstance(ObjectFactory.class).createUnmarshaller();
    Object obj = unmarshaller.unmarshal(response.getSOAPBody().extractContentAsDocument());
    return (O) obj;
  }

  /**
   * @param message The message to send to the TotalMobile webservice
   * @return A response to the message
   */
  private <O> O soapSend(SOAPMessage message) {
    try {
      // Create SOAP Connection
      SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
      SOAPConnection soapConnection = soapConnectionFactory.createConnection();

      // Add headers
      MimeHeaders hd = message.getMimeHeaders();

      // Add authentication
      if (username != null && password != null) {
        String authorization = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        hd.addHeader("Authorization", "Basic " + authorization);
      }

      // Send SOAP Message to SOAP Server
      SOAPMessage soapRequest = soapConnection.call(message, urlStart + urlEnd);

      // Print the SOAP Response
      if (debug_on) {
        System.out.println("Response SOAP Message:");
        soapRequest.writeTo(System.out);
        System.out.println();
      }

      soapConnection.close();

      return soapResponseUnmarshal(soapRequest);
    } catch (Exception e) {
      System.err.println(
          "\nError occurred while sending SOAP request to Server!\nMake sure you have the correct endpoint URL and SOAPAction!\n");
      e.printStackTrace();
      // nooooo
      System.exit(1);
    }
    return null;
  }

  /**
   * @param requests A list of requests to be submitted
   * @return A list of responses for each request
   * @throws IOException 
   * @throws ParserConfigurationException 
   * @throws SOAPException 
   * @throws JAXBException 
   */
  public <I, O> List<O> sendAll(List<I> requests) throws Exception {
    List<O> list = new ArrayList<>();

    for (I visit : requests) {
      list.add(this.send(visit));
    }

    return list;
  }

  /**
   * @param request Request to be sent to webservice
   * @return Response from webservice
   * @throws javax.xml.bind.JAXBException
   * @throws javax.xml.soap.SOAPException
   * @throws javax.xml.parsers.ParserConfigurationException
   * @throws java.io.IOException
   */
  public <I, O> O send(I request) throws javax.xml.bind.JAXBException, javax.xml.soap.SOAPException,
      javax.xml.parsers.ParserConfigurationException, java.io.IOException {
    // Create the document, then the SOAP wrapper
    Document document = toDocument(request);
    SOAPMessage soap = soapConstruct(document, lookupAction(request));
    // Perform the send
    // soap.toString();
    return soapSend(soap);
  }
}