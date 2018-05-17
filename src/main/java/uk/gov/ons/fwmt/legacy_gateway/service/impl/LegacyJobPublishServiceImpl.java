package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyCollectionType;
import com.consiliumtechnologies.schemas.mobile._2009._03.visitstypes.AdditionalPropertyType;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.ObjectFactory;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisetypes.*;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateJobHeaderRequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacySampleIngest;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMJobEntity;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.error.UnknownUserException;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.LegacyJobPublishService;
import uk.gov.ons.fwmt.legacy_gateway.service.TMService;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.GregorianCalendar;
import java.util.List;

@Slf4j
@Service
public class LegacyJobPublishServiceImpl implements LegacyJobPublishService {
  private static final String JOB_SKILL = "Survey";
  private static final String JOB_WORK_TYPE = "SS";
  private static final String JOB_WORLD = "Default";
  private static final String JOB_QUEUE = "\\OPTIMISE\\INPUT";

  private final TMService tmService;
  private final TMJobRepo tmJobRepo;
  private final TMUserRepo tmUserRepo;

  private final DatatypeFactory datatypeFactory = DatatypeFactory.newInstance();
  private final ObjectFactory factory = new ObjectFactory();

  @Autowired
  public LegacyJobPublishServiceImpl(TMService tmService, TMJobRepo tmJobRepo, TMUserRepo tmUserRepo)
      throws DatatypeConfigurationException {
    this.tmService = tmService;
    this.tmJobRepo = tmJobRepo;
    this.tmUserRepo = tmUserRepo;
  }

  protected String getUsername(LegacySampleIngest ingest) {
    TMUserEntity entity = tmUserRepo.findByAuthNo(ingest.getAuth());
    if (entity == null) {
      throw new UnknownUserException(ingest.getAuth());
    }
    return entity.getTmUsername();
  }

  protected void addAdditionalProperty(CreateJobRequest request, String key, String value) {
    AdditionalPropertyType propertyType = new AdditionalPropertyType();
    propertyType.setName(key);
    propertyType.setValue(value);
    request.getJob().getAdditionalProperties().getAdditionalProperty().add(propertyType);
  }

  protected CreateJobRequest createJobRequestFromIngest(LegacySampleIngest ingest, String username) {
    // Setup the request object and all inner objects
    CreateJobRequest request = new CreateJobRequest();
    JobType job = new JobType();
    request.setJob(job);
    job.setLocation(new LocationType());
    job.setIdentity(new JobIdentityType());
//    job.setMandatoryResource(new ResourceIdentityType());
    job.getLocation().setAddressDetail(new AddressDetailType());
    job.getLocation().getAddressDetail().setLines(new AddressDetailType.Lines());
    job.setContact(new ContactInfoType());
    job.setAttributes(new NameValueAttributeCollectionType());
    job.setAllocatedTo(new ResourceIdentityType());
    job.setSkills(new SkillCollectionType());
    job.setAdditionalProperties(new AdditionalPropertyCollectionType());
    job.setWorld(new WorldIdentityType());

    // Set the job id
    request.getJob().getIdentity().setReference(ingest.getTmJobId());

    // Set the job location
    LocationType location = request.getJob().getLocation();
    List<String> addressLines = location.getAddressDetail().getLines().getAddressLine();
    addressLines.add(ingest.getAddressLine1());
    addressLines.add(ingest.getAddressLine2());
    addressLines.add(ingest.getAddressLine3());
    addressLines.add(ingest.getAddressLine4());
    location.getAddressDetail().setPostCode(ingest.getPostcode());
    location.setReference(ingest.getSerNo());
//    request.getJob().getLocation().getAddressDetail().setGeoX(factory.createAddressDetailTypeGeoX(ingest.getGeoX()));
//    request.getJob().getLocation().getAddressDetail().setGeoY(factory.createAddressDetailTypeGeoY(ingest.getGeoY()));

    // Set the job contact
    request.getJob().getContact().setName(ingest.getPostcode());

    // Set the job skills
    request.getJob().getSkills().getSkill().add(JOB_SKILL);

    // Set the job work type
    request.getJob().setWorkType(JOB_WORK_TYPE);

    // Set the job world
    request.getJob().getWorld().setReference(JOB_WORLD);

    // Set the job due date
    GregorianCalendar dueDateCalendar = new GregorianCalendar();
    dueDateCalendar.setTime(ingest.getDueDate());
    request.getJob().setDueDate(datatypeFactory.newXMLGregorianCalendar(dueDateCalendar));
//    request.getJob().setDueDate("2018-05-31T00:00:00+01:00");

    // Set the job description
    request.getJob().setDescription(ingest.getTla());

    // Set the allocated interviewer
    request.getJob().getAllocatedTo().setUsername(username);

    // additional properties
    // TODO these could be detected by looking for JobAdditionalProperty annotations
    // LFS
    addAdditionalProperty(request, "serno", ingest.getSerNo());
    addAdditionalProperty(request, "TLA", ingest.getTla());
    addAdditionalProperty(request, "week", ingest.getStage());
    addAdditionalProperty(request, "wave", ingest.getWave());
    addAdditionalProperty(request, "prem1", ingest.getAddressLine1());
    addAdditionalProperty(request, "prem2", ingest.getAddressLine2());
    addAdditionalProperty(request, "prem3", ingest.getAddressLine3());
    addAdditionalProperty(request, "prem4", ingest.getAddressLine4());
    addAdditionalProperty(request, "district", ingest.getDistrict());
    addAdditionalProperty(request, "postTown", ingest.getPostTown());
    addAdditionalProperty(request, "postCode", ingest.getPostcode());
    addAdditionalProperty(request, "quotaNo", ingest.getQuota());
    addAdditionalProperty(request, "addressNo", ingest.getAddressNo());
    addAdditionalProperty(request, "geoX", ingest.getGeoX().toString());
    addAdditionalProperty(request, "geoY", ingest.getGeoY().toString());
    addAdditionalProperty(request, "year", ingest.getYear());
    addAdditionalProperty(request, "month", ingest.getMonth());
    addAdditionalProperty(request, "contactNo", ingest.getTelNo());
    switch (ingest.getLegacySampleSurveyType()) {
    case GFF:
      addAdditionalProperty(request, "respondentName", ingest.getGffData().getName());
      addAdditionalProperty(request, "localAuthority", ingest.getGffData().getLauaName());
      // TODO does this need extra mapping?
      addAdditionalProperty(request, "splitSampleType", ingest.getGffData().getSubSample());
      addAdditionalProperty(request, "gridSelection", ingest.getGffData().getRand());
      addAdditionalProperty(request, "kishGridAdult2", ingest.getGffData().getAdult2());
      addAdditionalProperty(request, "kishGridAdult3", ingest.getGffData().getAdult3());
      addAdditionalProperty(request, "kishGridAdult4", ingest.getGffData().getAdult4());
      addAdditionalProperty(request, "kishGridAdult5", ingest.getGffData().getAdult5());
      addAdditionalProperty(request, "kishGridAdult6", ingest.getGffData().getAdult6());
      addAdditionalProperty(request, "kishGridAdult7", ingest.getGffData().getAdult7());
      addAdditionalProperty(request, "kishGridAdult8", ingest.getGffData().getAdult8());
      addAdditionalProperty(request, "kishGridAdult9", ingest.getGffData().getAdult9());
      addAdditionalProperty(request, "kishGridAdult10", ingest.getGffData().getAdult10());
      addAdditionalProperty(request, "kishGridAdult11", ingest.getGffData().getAdult11());
      addAdditionalProperty(request, "kishGridAdult12", ingest.getGffData().getAdult12());
      addAdditionalProperty(request, "kishGridAdult13", ingest.getGffData().getAdult13());
      addAdditionalProperty(request, "kishGridAdult14", ingest.getGffData().getAdult14());
      break;
    case LFS:
      addAdditionalProperty(request, "referenceDate", ingest.getLfsData().getRefDate());
      addAdditionalProperty(request, "lstHo", ingest.getLfsData().getLstho());
      addAdditionalProperty(request, "mainResp", ingest.getLfsData().getMain());
      addAdditionalProperty(request, "numHhld", ingest.getLfsData().getNumberHouseholds());
      addAdditionalProperty(request, "hhldDesc", ingest.getLfsData().getHouseholdsDesc());
      addAdditionalProperty(request, "respondentName1", ingest.getLfsData().getRespondentName1());
      addAdditionalProperty(request, "respondentName2", ingest.getLfsData().getRespondentName2());
      addAdditionalProperty(request, "respondentName3", ingest.getLfsData().getRespondentName3());
      addAdditionalProperty(request, "respondentName4", ingest.getLfsData().getRespondentName4());
      addAdditionalProperty(request, "respondentName5", ingest.getLfsData().getRespondentName5());
      addAdditionalProperty(request, "respondentName6", ingest.getLfsData().getRespondentName6());
      addAdditionalProperty(request, "respondentName7", ingest.getLfsData().getRespondentName7());
      addAdditionalProperty(request, "respondentName8", ingest.getLfsData().getRespondentName8());
      addAdditionalProperty(request, "respondentName9", ingest.getLfsData().getRespondentName9());
      addAdditionalProperty(request, "respondentName10", ingest.getLfsData().getRespondentName10());
      addAdditionalProperty(request, "respondentName11", ingest.getLfsData().getRespondentName11());
      addAdditionalProperty(request, "respondentName12", ingest.getLfsData().getRespondentName12());
      addAdditionalProperty(request, "respondentName13", ingest.getLfsData().getRespondentName13());
      addAdditionalProperty(request, "respondentName14", ingest.getLfsData().getRespondentName14());
      addAdditionalProperty(request, "respondentName15", ingest.getLfsData().getRespondentName15());
      addAdditionalProperty(request, "respondentName16", ingest.getLfsData().getRespondentName16());
      addAdditionalProperty(request, "respondentAge1", ingest.getLfsData().getRespondentAge1());
      addAdditionalProperty(request, "respondentAge2", ingest.getLfsData().getRespondentAge2());
      addAdditionalProperty(request, "respondentAge3", ingest.getLfsData().getRespondentAge3());
      addAdditionalProperty(request, "respondentAge4", ingest.getLfsData().getRespondentAge4());
      addAdditionalProperty(request, "respondentAge5", ingest.getLfsData().getRespondentAge5());
      addAdditionalProperty(request, "respondentAge6", ingest.getLfsData().getRespondentAge6());
      addAdditionalProperty(request, "respondentAge7", ingest.getLfsData().getRespondentAge7());
      addAdditionalProperty(request, "respondentAge8", ingest.getLfsData().getRespondentAge8());
      addAdditionalProperty(request, "respondentAge9", ingest.getLfsData().getRespondentAge9());
      addAdditionalProperty(request, "respondentAge10", ingest.getLfsData().getRespondentAge10());
      addAdditionalProperty(request, "respondentAge11", ingest.getLfsData().getRespondentAge11());
      addAdditionalProperty(request, "respondentAge12", ingest.getLfsData().getRespondentAge12());
      addAdditionalProperty(request, "respondentAge13", ingest.getLfsData().getRespondentAge13());
      addAdditionalProperty(request, "respondentAge14", ingest.getLfsData().getRespondentAge14());
      addAdditionalProperty(request, "respondentAge15", ingest.getLfsData().getRespondentAge15());
      addAdditionalProperty(request, "respondentAge16", ingest.getLfsData().getRespondentAge16());
      addAdditionalProperty(request, "respondentWorkIndicator1", ingest.getLfsData().getRespondentWorkIndicator1());
      addAdditionalProperty(request, "respondentWorkIndicator2", ingest.getLfsData().getRespondentWorkIndicator2());
      addAdditionalProperty(request, "respondentWorkIndicator3", ingest.getLfsData().getRespondentWorkIndicator3());
      addAdditionalProperty(request, "respondentWorkIndicator4", ingest.getLfsData().getRespondentWorkIndicator4());
      addAdditionalProperty(request, "respondentWorkIndicator5", ingest.getLfsData().getRespondentWorkIndicator5());
      addAdditionalProperty(request, "respondentWorkIndicator6", ingest.getLfsData().getRespondentWorkIndicator6());
      addAdditionalProperty(request, "respondentWorkIndicator7", ingest.getLfsData().getRespondentWorkIndicator7());
      addAdditionalProperty(request, "respondentWorkIndicator8", ingest.getLfsData().getRespondentWorkIndicator8());
      addAdditionalProperty(request, "respondentWorkIndicator9", ingest.getLfsData().getRespondentWorkIndicator9());
      addAdditionalProperty(request, "respondentWorkIndicator10", ingest.getLfsData().getRespondentWorkIndicator10());
      addAdditionalProperty(request, "respondentWorkIndicator11", ingest.getLfsData().getRespondentWorkIndicator11());
      addAdditionalProperty(request, "respondentWorkIndicator12", ingest.getLfsData().getRespondentWorkIndicator12());
      addAdditionalProperty(request, "respondentWorkIndicator13", ingest.getLfsData().getRespondentWorkIndicator13());
      addAdditionalProperty(request, "respondentWorkIndicator14", ingest.getLfsData().getRespondentWorkIndicator14());
      addAdditionalProperty(request, "respondentWorkIndicator15", ingest.getLfsData().getRespondentWorkIndicator15());
      addAdditionalProperty(request, "respondentWorkIndicator16", ingest.getLfsData().getRespondentWorkIndicator16());
      addAdditionalProperty(request, "respondentLookingForWork1", ingest.getLfsData().getRespondentLookingForWork1());
      addAdditionalProperty(request, "respondentLookingForWork2", ingest.getLfsData().getRespondentLookingForWork2());
      addAdditionalProperty(request, "respondentLookingForWork3", ingest.getLfsData().getRespondentLookingForWork3());
      addAdditionalProperty(request, "respondentLookingForWork4", ingest.getLfsData().getRespondentLookingForWork4());
      addAdditionalProperty(request, "respondentLookingForWork5", ingest.getLfsData().getRespondentLookingForWork5());
      addAdditionalProperty(request, "respondentLookingForWork6", ingest.getLfsData().getRespondentLookingForWork6());
      addAdditionalProperty(request, "respondentLookingForWork7", ingest.getLfsData().getRespondentLookingForWork7());
      addAdditionalProperty(request, "respondentLookingForWork8", ingest.getLfsData().getRespondentLookingForWork8());
      addAdditionalProperty(request, "respondentLookingForWork9", ingest.getLfsData().getRespondentLookingForWork9());
      addAdditionalProperty(request, "respondentLookingForWork10", ingest.getLfsData().getRespondentLookingForWork10());
      addAdditionalProperty(request, "respondentLookingForWork11", ingest.getLfsData().getRespondentLookingForWork11());
      addAdditionalProperty(request, "respondentLookingForWork12", ingest.getLfsData().getRespondentLookingForWork12());
      addAdditionalProperty(request, "respondentLookingForWork13", ingest.getLfsData().getRespondentLookingForWork13());
      addAdditionalProperty(request, "respondentLookingForWork14", ingest.getLfsData().getRespondentLookingForWork14());
      addAdditionalProperty(request, "respondentLookingForWork15", ingest.getLfsData().getRespondentLookingForWork15());
      addAdditionalProperty(request, "respondentLookingForWork16", ingest.getLfsData().getRespondentLookingForWork16());
      addAdditionalProperty(request, "respondentInterviewType1", ingest.getLfsData().getRespondentInterviewType1());
      addAdditionalProperty(request, "respondentInterviewType2", ingest.getLfsData().getRespondentInterviewType2());
      addAdditionalProperty(request, "respondentInterviewType3", ingest.getLfsData().getRespondentInterviewType3());
      addAdditionalProperty(request, "respondentInterviewType4", ingest.getLfsData().getRespondentInterviewType4());
      addAdditionalProperty(request, "respondentInterviewType5", ingest.getLfsData().getRespondentInterviewType5());
      addAdditionalProperty(request, "respondentInterviewType6", ingest.getLfsData().getRespondentInterviewType6());
      addAdditionalProperty(request, "respondentInterviewType7", ingest.getLfsData().getRespondentInterviewType7());
      addAdditionalProperty(request, "respondentInterviewType8", ingest.getLfsData().getRespondentInterviewType8());
      addAdditionalProperty(request, "respondentInterviewType9", ingest.getLfsData().getRespondentInterviewType9());
      addAdditionalProperty(request, "respondentInterviewType10", ingest.getLfsData().getRespondentInterviewType10());
      addAdditionalProperty(request, "respondentInterviewType11", ingest.getLfsData().getRespondentInterviewType11());
      addAdditionalProperty(request, "respondentInterviewType12", ingest.getLfsData().getRespondentInterviewType12());
      addAdditionalProperty(request, "respondentInterviewType13", ingest.getLfsData().getRespondentInterviewType13());
      addAdditionalProperty(request, "respondentInterviewType14", ingest.getLfsData().getRespondentInterviewType14());
      addAdditionalProperty(request, "respondentInterviewType15", ingest.getLfsData().getRespondentInterviewType15());
      addAdditionalProperty(request, "respondentInterviewType16", ingest.getLfsData().getRespondentInterviewType16());
      addAdditionalProperty(request, "respondentBriefNotes", ingest.getLfsData().getRespondentBriefNotes());
      addAdditionalProperty(request, "waveInterviewer1", ingest.getLfsData().getComment1InterviewerNo());
      addAdditionalProperty(request, "waveInterviewer2", ingest.getLfsData().getComment2InterviewerNo());
      addAdditionalProperty(request, "waveInterviewer3", ingest.getLfsData().getComment3InterviewerNo());
      addAdditionalProperty(request, "waveInterviewer4", ingest.getLfsData().getComment4InterviewerNo());
      addAdditionalProperty(request, "waveInterviewer5", ingest.getLfsData().getComment5InterviewerNo());
      addAdditionalProperty(request, "waveBriefNotes1", ingest.getLfsData().getComment1BriefNotes1());
      addAdditionalProperty(request, "waveBriefNotes2", ingest.getLfsData().getComment2BriefNotes1());
      addAdditionalProperty(request, "waveBriefNotes3", ingest.getLfsData().getComment3BriefNotes1());
      addAdditionalProperty(request, "waveBriefNotes4", ingest.getLfsData().getComment4BriefNotes1());
      addAdditionalProperty(request, "waveBriefNotes5", ingest.getLfsData().getComment5BriefNotes1());
      addAdditionalProperty(request, "operationalParaData", ingest.getLfsData().getDirection());
      break;
    }

    // Set other required values
    request.getJob().setDuration(1);
    request.getJob().setVisitComplete(false);
    request.getJob().setDispatched(false);
    request.getJob().setAppointmentPending(false);
    request.getJob().setEmergency(false);

    return request;
  }

  protected UpdateJobHeaderRequest updateJobHeaderRequestFromIngest(LegacySampleIngest ingest, String username) {
    UpdateJobHeaderRequest request = new UpdateJobHeaderRequest();
    request.setJobHeader(new JobHeaderType());
    request.getJobHeader().setAllocatedTo(new ResourceIdentityType());
    request.getJobHeader().setJobIdentity(new JobIdentityType());

    request.getJobHeader().getAllocatedTo().setUsername(username);
    request.getJobHeader().getJobIdentity().setReference(ingest.getTmJobId());

    return request;
  }

  protected SendMessageRequestInfo makeSendMessageRequestInfo(String key) {
    SendMessageRequestInfo info = new SendMessageRequestInfo();
    info.setQueueName(JOB_QUEUE);
    info.setKey(key);
    return info;
  }

  protected void reallocateJob(LegacySampleIngest ingest) {
    String username = getUsername(ingest);
    UpdateJobHeaderRequest request = updateJobHeaderRequestFromIngest(ingest, username);

    SendUpdateJobHeaderRequestMessage message = new SendUpdateJobHeaderRequestMessage();
    message.setSendMessageRequestInfo(makeSendMessageRequestInfo(ingest.getTmJobId()));
    message.setUpdateJobHeaderRequest(request);

    // TODO re-enable this
//    tmService.send(message);

    // save the job into our database
    TMJobEntity entity = tmJobRepo.findByTmJobId(ingest.getTmJobId());
    // TODO update fields
    tmJobRepo.save(entity);
  }

  protected void newJob(LegacySampleIngest ingest) {
    String username = getUsername(ingest);
    CreateJobRequest request = createJobRequestFromIngest(ingest, username);

    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    message.setSendMessageRequestInfo(makeSendMessageRequestInfo(ingest.getTmJobId()));
    message.setCreateJobRequest(request);

    // TODO re-enable this
//    tmService.send(message);

    // save the job into our database
    TMJobEntity entity = new TMJobEntity();
    entity.setTmJobId(ingest.getTmJobId());
    tmJobRepo.save(entity);
  }

  public void publishJob(LegacySampleIngest job) {
    // only send if the user is active
    if (tmUserRepo.existsByAuthNoAndActive(job.getAuth(), true)) {
      log.info("User was active");
      // reallocate if we've seen this id before TODO and the authno changed
      if (tmJobRepo.existsByTmJobId(job.getTmJobId())) {
        log.info("Job is a reallocation");
        // reallocate
        reallocateJob(job);
      } else {
        log.info("Job is not a reallocation");
        switch (job.getLegacySampleSurveyType()) {
        case GFF:
          if (job.isGffReissue()) {
            // reissue
            // the date was modified within LegacySampleIngest
            // the rest of this code is identical to creating a new job
            log.info("Job is a GFF reissue");
            newJob(job);
          } else {
            // new job
            log.info("Job is a new GFF job");
            newJob(job);
          }
          break;
        case LFS:
          // new job
          log.info("Job is a new LFS job");
          newJob(job);
          break;
        }
      }
    } else {
      log.info("User was not active");
    }
  }
}

