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

public class PublishServiceImpl implements PublishService {

  private TMMessageSubmitter submitter;
  private LegacySampleRepo legacySampleRepository;
  private LegacyStaffRepo legacyStaffRepo;
//  private LegacyLeaversRepo legacyLeaversRepo;
  private LegacyJobsRepo legacyJobsRepo;
  private LegacyUsersRepo legacyUsersRepo;

  @Autowired
  public PublishServiceImpl(TMMessageSubmitter submitter, LegacySampleRepo legacySampleRepository,
      LegacyStaffRepo legacyStaffRepo, LegacyLeaversRepo legacyLeaversRepo, LegacyJobsRepo legacyJobsRepo,
      LegacyUsersRepo legacyUsersRepo) {
    this.submitter = submitter;
    this.legacySampleRepository = legacySampleRepository;
    this.legacyStaffRepo = legacyStaffRepo;
//    this.legacyLeaversRepo = legacyLeaversRepo;
    this.legacyJobsRepo = legacyJobsRepo;
    this.legacyUsersRepo = legacyUsersRepo;
  }

  public void executeUpdateUsers() {
    legacyStaffRepo.findAll().forEach(staff -> {
      if (!legacyUsersRepo.existsByAuthNo(staff.getAuthno())) {
        // create new user in TM
        // find if staff member already exists
        // if so then update their staffid
        if (legacyUsersRepo.existsByTmusername(getProposedTMUsername(staff.getEmail()))) {
          LegacyUserEntity existingUser = legacyUsersRepo.findByTmusername(getProposedTMUsername(staff.getEmail()));
          existingUser.setAuthNo(staff.getAuthno());
          legacyUsersRepo.save(existingUser);
        } else {
          // if not then create new user entity
          LegacyUserEntity newUser = LegacyUsersReader.createUserEntity(staff,
              getProposedTMUsername(staff.getEmail()));
          // create new mobilise user in TM

          // TODO CREATE THE USER IN TM USING THE ADMINWS OR SELENIUM
          System.out.println("Must create new user " + newUser.getTmusername() + " and set correct AuthNo.");
          // make sure to add authority number as an additional property

          // add to the users table
          legacyUsersRepo.save(newUser);
        }
      }
    });

    legacyUsersRepo.findAll().forEach(user ->

    {
      if (!legacyStaffRepo.existsByAuthno(user.getAuthNo())) {
        // remove user (potentially delete the user from totalmobile TBD)
        user.setAuthNo(null);
        legacyUsersRepo.save(user);
      }
    });
  }

  @Override
  public void publishNewJobsAndReallocations() {
    legacySampleRepository.findAll().forEach(entity -> {
      if (legacyJobsRepo.existsBySerno(entity.getSerno())) {
        executeReallocateJob(entity);
      } else {
        executeNewJob(entity);
      }
    });
  }

  public void executeNewJob(LegacySampleEntity newJobsEntity) {
    String tmUsername = legacyUsersRepo.findByAuthNo(newJobsEntity.getAuth()).getTmusername();
    CreateJobRequest createJobRequest = LegacyCreateJobRequestFactory.convert(newJobsEntity, tmUsername);
    LegacyJobEntity legacyJobEntity = LegacyJobsReader.createJobEntity(createJobRequest);
    legacyJobsRepo.save(legacyJobEntity);
    sendJobRequest(createJobRequest, "\\OPTIMISE\\INPUT");

    // Update database jobs table states for each job from inital to sent.
    LegacyJobEntity jobEntity = legacyJobsRepo.findByTmjobid(createJobRequest.getJob().getIdentity().getReference());
    legacyJobsRepo.save(LegacyJobsReader.setStateSent(jobEntity));
  }

  public void executeReallocateJob(LegacySampleEntity reallocationEntity) {
    String tmJobId = legacyJobsRepo.findBySerno(reallocationEntity.getSerno()).getTmjobid();
    String tmUsername = legacyUsersRepo.findByAuthNo(reallocationEntity.getAuth()).getTmusername();
    UpdateJobHeaderRequest updateJobHeaderRequest = LegacyUpdateJobHeaderRequestFactory.reallocate(tmJobId, tmUsername);
    sendUpdateJobRequest(updateJobHeaderRequest, "\\OPTIMISE\\INPUT");

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

  private void sendJobRequest(CreateJobRequest request, String queueName) {
    List<SendCreateJobRequestMessage> messages = new ArrayList<SendCreateJobRequestMessage>();

    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    message.setCreateJobRequest(request);

    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
    message.getSendMessageRequestInfo().setQueueName(queueName);
    message.getSendMessageRequestInfo().setKey(request.getJob().getIdentity().getReference());
    messages.add(message);

    submitter.sendAll(messages);
  }

  private void sendUpdateJobRequest(UpdateJobHeaderRequest request, String queueName) {
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
