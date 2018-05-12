package uk.gov.ons.fwmt.legacy_gateway.utility.debug;

import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.CriteriaType;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ObjectFactory;
import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ParseAsType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyCollectionType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.DeleteJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.*;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.*;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.util.GregorianCalendar;

public class DebugMessage {
  public static SendCreateJobRequestMessage createSendCreateJobRequestMessage(String username, String[] addressLines,
      String postcode, String quota, String survey) throws Exception {
    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    ObjectFactory factory = new ObjectFactory();

    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
    message.getSendMessageRequestInfo().setQueueName("\\OPTIMISE\\INPUT");
    message.getSendMessageRequestInfo().setKey("Key");

    CreateJobRequest request = new CreateJobRequest();
    message.setCreateJobRequest(request);

    JobType job = new JobType();
    request.setJob(job);

    job.setAllocatedTo(new ResourceIdentityType());
    job.getAllocatedTo().setUsername(username);

    job.setIdentity(new JobIdentityType());
    job.getIdentity().setReference(quota);

    job.setSkills(new SkillCollectionType());
    job.getSkills().getSkill().add("Survey");

    job.setWorld(new WorldIdentityType());
    job.getWorld().setReference("Default");

    job.setLocation(new LocationType());
    job.getLocation().setAddressDetail(new AddressDetailType());
    AddressDetailType.Lines lines = new AddressDetailType.Lines();
    job.getLocation().getAddressDetail().setLines(lines);
    for (String line : addressLines) {
      lines.getAddressLine().add(line);
    }
    job.getLocation().getAddressDetail().setPostCode(postcode);

    // TODO remove OR configure (this was used for address resolution tests)
    // if (coordinates[0] != 0f) {
    // System.out.println("setting coords X: "+coordinates[0] + " and Y: "+
    // coordinates[1]);
    // job.getLocation().getAddressDetail().setGeoX(factory.createAddressTypeGeoX(coordinates[0]));
    // job.getLocation().getAddressDetail().setGeoY(factory.createAddressTypeGeoY(coordinates[1]));
    // }

    job.setContact(new ContactInfoType());
    job.getContact().setName(postcode);

    job.setWorkType("SS");
    job.setDescription(survey);
    job.setDuration(1);
    job.setVisitComplete(false);
    job.setDispatched(false);
    job.setAppointmentPending(false);
    job.setEmergency(false);

    GregorianCalendar cal = new GregorianCalendar();
    cal.set(118, 4, 1);
    XMLGregorianCalendar startDate =
        DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    job.setDueStart(startDate);

    // GregorianCalendar cal = new GregorianCalendar();
    // cal.setTime(new Date(2019, 3, 28));
    // XMLGregorianCalendar startDate =
    // DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
    // job.setDispatchDate(startDate);

    GregorianCalendar cal3 = new GregorianCalendar();
    cal3.set(118, 4, 30);
    XMLGregorianCalendar endDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal3);
    job.setDueEnd(endDate);

    GregorianCalendar cal2 = new GregorianCalendar();
    cal2.set(118, 4, 30);
    XMLGregorianCalendar due = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal2);
    job.setDueDate(due);

    // ADD A5 INFORMATION
    AdditionalPropertyCollectionType additionalPropertyCollectionType = new AdditionalPropertyCollectionType();
    AdditionalPropertyType tlaProperty = new AdditionalPropertyType();
    tlaProperty.setName("TLA");
    tlaProperty.setValue("FRS");
    AdditionalPropertyType laProperty = new AdditionalPropertyType();
    laProperty.setName("Local Authority");
    laProperty.setValue("Southampton Maybe");
    additionalPropertyCollectionType.getAdditionalProperty().add(tlaProperty);
    additionalPropertyCollectionType.getAdditionalProperty().add(laProperty);
    job.setAdditionalProperties(additionalPropertyCollectionType);

    return message;
  }

  public static JAXBElement<QueryMessagesRequest> createQueryMessagesRequest(String property, String value) {
    com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory objectFactory = new com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory();

    QueryMessagesRequest queryMessagesRequest = new QueryMessagesRequest();
    ArrayOfCriteriaType arrayOfCriteriaType = new ArrayOfCriteriaType();
    CriteriaType criteria = new CriteriaType();

    criteria.setProperty(property);
    criteria.setValue(value);
    criteria.setParseAs(ParseAsType.STRING);

    //        CriteriaType whoCriteria = new CriteriaType();

    //        whoCriteria.setProperty("");
    //        whoCriteria.setValue("tracyhobson");
    //        whoCriteria.setParseAs(ParseAsType.STRING);

    arrayOfCriteriaType.getCriterion().add(criteria);
    //        arrayOfCriteriaType.getCriterion().add(whoCriteria);
    queryMessagesRequest.setCriteria(arrayOfCriteriaType);

    return objectFactory.createQueryMessagesRequest(queryMessagesRequest);
  }

  public static JAXBElement<QueryMessagesRequest> createQueryMessagesUnallocatedRequest(String property,
      String value) {
    com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory objectFactory = new com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory();

    QueryMessagesRequest queryMessagesRequest = new QueryMessagesRequest();
    ArrayOfCriteriaType arrayOfCriteriaType = new ArrayOfCriteriaType();
    CriteriaType criteria = new CriteriaType();

    criteria.setProperty(property);
    criteria.setValue(value);
    criteria.setParseAs(ParseAsType.STRING);

    arrayOfCriteriaType.getCriterion().add(criteria);
    queryMessagesRequest.setCriteria(arrayOfCriteriaType);

    return objectFactory.createQueryMessagesRequest(queryMessagesRequest);
  }

  public static JAXBElement<GetMessageRequest> createGetMessageRequest(String id) {
    com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory objectFactory = new com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.ObjectFactory();
    GetMessageRequest getMessageRequest = new GetMessageRequest();
    getMessageRequest.setId(id);
    getMessageRequest.setUseCDATA(false);
    return objectFactory.createGetMessageRequest(getMessageRequest);
  }

  public static SendDeleteJobRequestMessage createSendDeleteJobRequestMessage(String id) {
    SendDeleteJobRequestMessage sendDeleteJobRequestMessage = new SendDeleteJobRequestMessage();
    DeleteJobRequest deleteJobRequest = new DeleteJobRequest();
    JobIdentityType jobIdentityType = new JobIdentityType();
    jobIdentityType.setReference(id);
    deleteJobRequest.setIdentity(jobIdentityType);
    sendDeleteJobRequestMessage.setDeleteJobRequest(deleteJobRequest);
    sendDeleteJobRequestMessage.setSendMessageRequestInfo(new SendMessageRequestInfo());
    sendDeleteJobRequestMessage.getSendMessageRequestInfo().setQueueName("\\OPTIMISE\\INPUT");
    sendDeleteJobRequestMessage.getSendMessageRequestInfo().setKey("Test");

    return sendDeleteJobRequestMessage;
  }
}
