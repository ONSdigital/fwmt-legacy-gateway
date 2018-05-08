package uk.gov.ons.fwmt.gateway.service.impl;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.UpdateJobHeaderRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendUpdateJobHeaderRequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;
import uk.gov.ons.fwmt.gateway.error.UnknownUserException;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyJobsRepo;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyUsersRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacySampleRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;
import uk.gov.ons.fwmt.gateway.service.PublishService;
import uk.gov.ons.fwmt.gateway.utility.TMMessageSubmitter;
import uk.gov.ons.fwmt.gateway.utility.csvconverter.LegacyCreateJobRequestFactory;
import uk.gov.ons.fwmt.gateway.utility.csvconverter.LegacyUpdateJobHeaderRequestFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Slf4j
@Service
public class PublishServiceImpl implements PublishService {

  private TMMessageSubmitter submitter;
  private LegacySampleRepo legacySampleRepository;
  private LegacyStaffRepo legacyStaffRepo;
  private LegacyJobsRepo legacyJobsRepo;
  private LegacyUsersRepo legacyUsersRepo;

  // TODO refactor this, it's local state and should be in a local variable passed between functions
  private List<String> successfullySentIds;

  @Autowired
  public PublishServiceImpl(TMMessageSubmitter submitter, LegacySampleRepo legacySampleRepository,
      LegacyStaffRepo legacyStaffRepo, LegacyJobsRepo legacyJobsRepo, LegacyUsersRepo legacyUsersRepo) {
    this.submitter = submitter;
    this.legacySampleRepository = legacySampleRepository;
    this.legacyStaffRepo = legacyStaffRepo;
    this.legacyJobsRepo = legacyJobsRepo;
    this.legacyUsersRepo = legacyUsersRepo;
  }

  @Override
  public void publishUpdateUsers() {
    legacyStaffRepo.findAll().forEach(staff -> {
      if (!legacyUsersRepo.existsByAuthNo(staff.getAuthNo())) {
        if (legacyUsersRepo.existsByTmUsername(getProposedTMUsername(staff.getEmail()))) {
          LegacyUserEntity existingUser = legacyUsersRepo.findByTmUsername(getProposedTMUsername(staff.getEmail()));
          existingUser.setAuthNo(staff.getAuthNo());
          legacyUsersRepo.save(existingUser);
        } else {
          LegacyUserEntity newUser = new LegacyUserEntity(staff, getProposedTMUsername(staff.getEmail()));

          // TODO create new mobilise user in TM
          log.info("Must create new user " + newUser.getTmUsername() + " and set correct AuthNo.");
          // make sure to add authority number as an additional property
          legacyUsersRepo.save(newUser);
        }
      }
    });

    legacyUsersRepo.findAll().forEach(user -> {
      if (!legacyStaffRepo.existsByAuthNo(user.getAuthNo())) {
        // remove user (potentially delete the user from totalmobile TBD)
        user.setAuthNo(null);
        legacyUsersRepo.save(user);
      }
    });

    // all lines of the staff repo have been processed to find new users and
    // users that no longer exist.
    // therefore i will now empty the staff reception table
    legacyStaffRepo.deleteAll();
  }

  @Override
  public void publishNewJobsReallocationsAndReissues() {
    legacySampleRepository.findAll().forEach(entity -> {
      if (legacyUsersRepo.existsByAuthNo(entity.getAuthNo())) {
        if (legacyJobsRepo.existsByLegacyJobId(entity.getLegacyJobId())) {
          if (executeReallocateJob(entity)) {
            legacySampleRepository.deleteByLegacyJobId(entity.getLegacyJobId());
          }
        } else {
          // may need to identify reissues in order to reference previous jobs
          if (executeNewJob(entity)) {
            legacySampleRepository.deleteByLegacyJobId(entity.getLegacyJobId());
          }
        }
      }
    });
  }

  public boolean executeNewJob(LegacySampleEntity newJobEntity) {
    LegacyUserEntity user = legacyUsersRepo.findByAuthNo(newJobEntity.getAuthNo());
    if (user == null) {
      throw new UnknownUserException(newJobEntity.getAuthNo());
    }
    String tmUsername = user.getTmUsername();
    CreateJobRequest createJobRequest = LegacyCreateJobRequestFactory.convert(newJobEntity, tmUsername);
    LegacyJobEntity legacyJobEntity = new LegacyJobEntity(createJobRequest);
    legacyJobsRepo.save(legacyJobEntity);
    try {
      // TODO re-enable this once TM support is enabled
      // sendJobRequest(createJobRequest, "\\OPTIMISE\\INPUT");
    } catch (Exception e) {
      // something errored do something here
      e.printStackTrace();
      return false;
    }

    // Update database jobs table states for each job from inital to sent.
    LegacyJobEntity jobEntity = legacyJobsRepo.findByTmJobId(createJobRequest.getJob().getIdentity().getReference());
    jobEntity.setStateSent();
    legacyJobsRepo.save(jobEntity);
    return true;
  }

  public boolean executeReallocateJob(LegacySampleEntity reallocationEntity) {
    String tmJobId = legacyJobsRepo.findByLegacyJobId(reallocationEntity.getLegacyJobId()).getTmJobId();
    String tmUsername = legacyUsersRepo.findByAuthNo(reallocationEntity.getAuthNo()).getTmUsername();
    UpdateJobHeaderRequest updateJobHeaderRequest = LegacyUpdateJobHeaderRequestFactory.reallocate(tmJobId, tmUsername);
    try {
      // TODO re-enable this once TM support is enabled
      // sendUpdateJobRequest(updateJobHeaderRequest, "\\OPTIMISE\\INPUT");
      successfullySentIds.add(reallocationEntity.getLegacyJobId());
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }

    // Update database jobs table states for each job from inital to sent.
    LegacyJobEntity jobEntity = legacyJobsRepo
        .findByTmJobId(updateJobHeaderRequest.getJobHeader().getJobIdentity().getReference());
    jobEntity.setStateSent();
    legacyJobsRepo.save(jobEntity);
    return true;
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
    List<SendCreateJobRequestMessage> messages = new ArrayList<>();

    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
    message.setCreateJobRequest(request);

    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
    message.getSendMessageRequestInfo().setQueueName(queueName);
    message.getSendMessageRequestInfo().setKey(request.getJob().getIdentity().getReference());
    messages.add(message);

    submitter.sendAll(messages);
  }

  private void sendUpdateJobRequest(UpdateJobHeaderRequest request, String queueName) throws Exception {
    List<SendUpdateJobHeaderRequestMessage> messages = new ArrayList<>();

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
