package uk.gov.ons.fwmt.gateway.utility.csvconverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.consiliumtechnologies.schemas.mobile._2009._03.commontypes.ObjectFactory;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyCollectionType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.AddressDetailType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.ContactInfoType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobIdentityType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.JobType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.LocationType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.NameValueAttributeCollectionType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.ResourceIdentityType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.SkillCollectionType;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;

public class LegacyCreateJobRequestFactory {

    private static List<LegacyUserEntity> allUsers;
    
    /**
     * Build the initial outlive of a CreateJobRequest
     */
    private static CreateJobRequest buildRequest() {
        CreateJobRequest request = new CreateJobRequest();
        JobType job = new JobType();
        request.setJob(job);
        job.setLocation(new LocationType());
        job.setIdentity(new JobIdentityType());
        job.setMandatoryResource(new ResourceIdentityType());
        job.getLocation().setAddressDetail(new AddressDetailType());
        job.getLocation().getAddressDetail().setLines(new AddressDetailType.Lines());
        job.setContact(new ContactInfoType());
        job.setAttributes(new NameValueAttributeCollectionType());
        job.setAllocatedTo(new ResourceIdentityType());
        job.setSkills(new SkillCollectionType());

        return request;
    }

    /**
     * Fill the initial outline of a CreateJobRequest with the data from some sample
     * data
     * 
     * @throws DatatypeConfigurationException
     */
    private static CreateJobRequest buildRequestFromSampleData(LegacySampleEntity entry)
            throws DatatypeConfigurationException {
        ObjectFactory factory = new ObjectFactory();
        CreateJobRequest request = buildRequest();

        // identity
        request.getJob().getIdentity()
                .setReference(entry.getQuota() + "-" + entry.getAddr() + "-" + entry.getFp());

        // location
        LocationType location = request.getJob().getLocation();
        List<String> addressLines = location.getAddressDetail().getLines().getAddressLine();
        addressLines.add(entry.getPrem1());
        addressLines.add(entry.getPrem2());
        addressLines.add(entry.getPrem3());
        addressLines.add(entry.getPrem4());
        location.getAddressDetail().setPostCode(entry.getPostcode());
        location.setReference(entry.getSerno());

        Float geoX = Float.parseFloat(entry.getOsgridref().toString().substring(0, 6));
        Float geoY = Float.parseFloat(entry.getOsgridref().toString().substring(7));

        request.getJob().getLocation().getAddressDetail().setGeoX(factory.createAddressTypeGeoX(geoX));
        request.getJob().getLocation().getAddressDetail().setGeoY(factory.createAddressTypeGeoX(geoY));

        request.getJob().getContact().setName(entry.getPostcode());

        // skills
        request.getJob().getSkills().getSkill().add("LegacySurvey");

        Date dueDate = fpToDates(entry.getFp());

        GregorianCalendar dueDateCalendar = new GregorianCalendar();
        dueDateCalendar.setTime(dueDate);
        XMLGregorianCalendar dueDateGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(dueDateCalendar);
        request.getJob().setDueDate(dueDateGC);

        // other required values
        request.getJob().setVisitComplete(false);
        request.getJob().setDescription(entry.getTla());
        request.getJob().setDuration(1);
        request.getJob().setWorkType("SS");
        request.getJob().setDispatched(false);
        request.getJob().setAppointmentPending(false);
        request.getJob().setEmergency(false);

        // additional properties
        AdditionalPropertyCollectionType additionalPropertyCollectionType = new AdditionalPropertyCollectionType();
        AdditionalPropertyType additionalProperty = new AdditionalPropertyType();
        additionalProperty.setName("TLA");
        additionalProperty.setValue(entry.getTla());
        additionalPropertyCollectionType.getAdditionalProperty().add(additionalProperty);
        request.getJob().setAdditionalProperties(additionalPropertyCollectionType);

        // interviewer allocation
        ResourceIdentityType resourceIdentityType = new ResourceIdentityType();
        resourceIdentityType.setUsername(staffIdToTMUsername(entry.getAuth()));
        request.getJob().setAllocatedTo(resourceIdentityType);
        
        return request;
    }

    private static Date fpToDates(String stage) {
        int year = Integer.parseInt(stage.substring(0, 1));
        int month = Integer.parseInt(stage.substring(1, 3));
        Calendar cal = Calendar.getInstance();
        cal.set(2010 + year, month, cal.getActualMaximum(Calendar.DATE));
        return cal.getTime();
    }

    private static String staffIdToTMUsername(String authNo) {
        for (LegacyUserEntity user : allUsers) {
            if (user.getAuthno().equals(authNo)) {
                return user.getTmusername();
            }
        }
        return null;
    }

    public static List<CreateJobRequest> convert(List<LegacySampleEntity> samples,
            List<LegacySampleEntity> allocations, List<LegacyUserEntity> users) {
        allUsers = users;
        
        List<CreateJobRequest> jobs = new ArrayList<>();

        // convert all of the samples into CreateJobRequests
        for (LegacySampleEntity sampleEntry : samples) {
            try {
                jobs.add(buildRequestFromSampleData(sampleEntry));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (jobs.size() > 0) {
            throw new AssertionError("No sample data was matched with allocation data");
        }

        return jobs;
    }
}
