package uk.gov.ons.fwmt.gateway.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateJobHeaderRequestMessage;

import lombok.extern.slf4j.Slf4j;
import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyJobsRepo;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyUsersRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyLeaversRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacySampleRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;
import uk.gov.ons.fwmt.gateway.service.PublishService;
import uk.gov.ons.fwmt.gateway.utility.TMMessageSubmitter;
import uk.gov.ons.fwmt.gateway.utility.csvconverter.LegacyCreateJobRequestFactory;
import uk.gov.ons.fwmt.gateway.utility.csvconverter.LegacyUpdateJobHeaderRequestFactory;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyJobsReader;
import uk.gov.ons.fwmt.gateway.utility.readers.LegacyUsersReader;

@Slf4j
public class PublishServiceImpl implements PublishService {

  private TMMessageSubmitter submitter;
  private LegacySampleRepo legacySampleRepository;
  private LegacyStaffRepo legacyStaffRepo;
  private LegacyJobsRepo legacyJobsRepo;
  private LegacyUsersRepo legacyUsersRepo;

  private List<String> successfullySentIds;

  @Autowired
  public PublishServiceImpl(TMMessageSubmitter submitter, LegacySampleRepo legacySampleRepository,
      LegacyStaffRepo legacyStaffRepo, LegacyLeaversRepo legacyLeaversRepo, LegacyJobsRepo legacyJobsRepo,
      LegacyUsersRepo legacyUsersRepo) {
    this.submitter = submitter;
    this.legacySampleRepository = legacySampleRepository;
    this.legacyStaffRepo = legacyStaffRepo;
    this.legacyJobsRepo = legacyJobsRepo;
    this.legacyUsersRepo = legacyUsersRepo;
  }

  @Override
  public void publishUpdateUsers() {
    legacyStaffRepo.findAll().forEach(staff -> {
      if (!legacyUsersRepo.existsByAuthNo(staff.getAuthno())) {
        if (legacyUsersRepo.existsByTmusername(getProposedTMUsername(staff.getEmail()))) {
          LegacyUserEntity existingUser = legacyUsersRepo.findByTmusername(getProposedTMUsername(staff.getEmail()));
          existingUser.setAuthNo(staff.getAuthno());
          legacyUsersRepo.save(existingUser);
        } else {
          LegacyUserEntity newUser = LegacyUsersReader.createUserEntity(staff,
              getProposedTMUsername(staff.getEmail()));

          // TODO create new mobilise user in TM
          log.info("Must create new user " + newUser.getTmusername() + " and set correct AuthNo.");
          // make sure to add authority number as an additional property
          legacyUsersRepo.save(newUser);
        }
      }
    });

    legacyUsersRepo.findAll().forEach(user -> {
      if (!legacyStaffRepo.existsByAuthno(user.getAuthNo())) {
        // remove user (potentially delete the user from totalmobile TBD)
        user.setAuthNo(null);
        legacyUsersRepo.save(user);
      }
    });

    // all lines of the staff repo have been processed to find new users and users that no longer exist.
    // therefore i will now empty the staff reception table
    legacyStaffRepo.deleteAll();
  }

  @Override
  public void publishNewJobsAndReallocations() {
    successfullySentIds = new ArrayList<String>();
    legacySampleRepository.findAll().forEach(entity -> {
      if (legacyJobsRepo.existsBySerno(entity.getSerno())) {
        executeReallocateJob(entity);
      } else {
        executeNewJob(entity);
      }
    });
    for(String id: successfullySentIds) {
      legacySampleRepository.deleteBySerno(id);
    }
  }

  public void executeNewJob(LegacySampleEntity newJobEntity) {
    String tmUsername = legacyUsersRepo.findByAuthNo(newJobEntity.getAuthno()).getTmusername();
    CreateJobRequest createJobRequest = LegacyCreateJobRequestFactory.convert(newJobEntity, tmUsername);
    LegacyJobEntity legacyJobEntity = LegacyJobsReader.createJobEntity(createJobRequest);
    legacyJobsRepo.save(legacyJobEntity);
    try {
      sendJobRequest(createJobRequest, "\\OPTIMISE\\INPUT");
      // TODO CHANGE THIS TO NEW COMPOSITE PK
      successfullySentIds.add(newJobEntity.getSerno());
    } catch (Exception e) {
      // something errored do something here
      e.printStackTrace();
    }

    // Update database jobs table states for each job from inital to sent.
    LegacyJobEntity jobEntity = legacyJobsRepo.findByTmjobid(createJobRequest.getJob().getIdentity().getReference());
    legacyJobsRepo.save(LegacyJobsReader.setStateSent(jobEntity));
  }

  public void executeReallocateJob(LegacySampleEntity reallocationEntity) {
    String tmJobId = legacyJobsRepo.findBySerno(reallocationEntity.getSerno()).getTmjobid();
    String tmUsername = legacyUsersRepo.findByAuthNo(reallocationEntity.getAuthno()).getTmusername();
    UpdateJobHeaderRequest updateJobHeaderRequest = LegacyUpdateJobHeaderRequestFactory.reallocate(tmJobId, tmUsername);
    try {
      sendUpdateJobRequest(updateJobHeaderRequest, "\\OPTIMISE\\INPUT");
      // TODO CHANGE THIS TO NEW COMPOSITE PK
      successfullySentIds.add(reallocationEntity.getSerno());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Update database jobs table states for each job from inital to sent.
    LegacyJobEntity jobEntity = legacyJobsRepo
        .findByTmjobid(updateJobHeaderRequest.getJobHeader().getJobIdentity().getReference());
    legacyJobsRepo.save(LegacyJobsReader.setStateSent(jobEntity));
  }

  public void executeReissueJobs() {
    // this may or may not be required
  }

  private <T> List<T> getAllEntities(Iterator<T> iter) {
    List<T> entities = new ArrayList<>();
    while (iter.hasNext()) {
      entities.add(iter.next());
    }
    return entities;
  }

  private void sendJobRequest(CreateJobRequest request, String queueName) throws Exception {
    List<SendCreateJobRequestMessage> messages = new ArrayList<SendCreateJobRequestMessage>();

    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    message.setCreateJobRequest(request);

    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
    message.getSendMessageRequestInfo().setQueueName(queueName);
    message.getSendMessageRequestInfo().setKey(request.getJob().getIdentity().getReference());
    messages.add(message);

    submitter.sendAll(messages);
  }

  private void sendUpdateJobRequest(UpdateJobHeaderRequest request, String queueName) throws Exception {
    List<SendUpdateJobHeaderRequestMessage> messages = new ArrayList<SendUpdateJobHeaderRequestMessage>();

    SendUpdateJobHeaderRequestMessage message = new SendUpdateJobHeaderRequestMessage();
    message.setUpdateJobHeaderRequest(request);

    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
    message.getSendMessageRequestInfo().setQueueName(queueName);
    message.getSendMessageRequestInfo().setKey(request.getJobHeader().getJobIdentity().getReference());

    submitter.sendAll(messages);
  }

  private String getProposedTMUsername(String email) {
    String username = email.split("@")[0];
    return username;
  }
}
