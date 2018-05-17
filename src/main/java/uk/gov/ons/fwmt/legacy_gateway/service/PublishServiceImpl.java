package uk.gov.ons.fwmt.legacy_gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Deprecated
@Slf4j
@Service
public class PublishServiceImpl {

//  private LegacyStaffRepo legacyStaffRepo;
//  private LegacyJobsRepo legacyJobsRepo;
//  private LegacyUsersRepo legacyUsersRepo;
//
//  @Autowired
//  public PublishServiceImpl(LegacyStaffRepo legacyStaffRepo, LegacyJobsRepo legacyJobsRepo,
//      LegacyUsersRepo legacyUsersRepo) {
//    this.legacyStaffRepo = legacyStaffRepo;
//    this.legacyJobsRepo = legacyJobsRepo;
//    this.legacyUsersRepo = legacyUsersRepo;
//  }
//
//  public void publishUpdateUsers() {
//    legacyStaffRepo.findAll().forEach(staff -> {
//      if (!legacyUsersRepo.existsByAuthNo(staff.getAuthNo())) {
//        if (legacyUsersRepo.existsByTmUsername(getProposedTMUsername(staff.getEmail()))) {
//          LegacyUserEntity existingUser = legacyUsersRepo.findByTmUsername(getProposedTMUsername(staff.getEmail()));
//          existingUser.setAuthNo(staff.getAuthNo());
//          legacyUsersRepo.save(existingUser);
//        } else {
//          LegacyUserEntity newUser = new LegacyUserEntity(staff, getProposedTMUsername(staff.getEmail()));
//
//          // TODO create new mobilise user in TM
//          log.info("Must create new user " + newUser.getTmUsername() + " and set correct AuthNo.");
//          // make sure to add authority number as an additional property
//          legacyUsersRepo.save(newUser);
//        }
//      }
//    });
//
//    legacyUsersRepo.findAll().forEach(user -> {
//      if (!legacyStaffRepo.existsByAuthNo(user.getAuthNo())) {
//        // remove user (potentially delete the user from totalmobile TBD)
//        user.setAuthNo(null);
//        legacyUsersRepo.save(user);
//      }
//    });
//
//    // all lines of the staff repo have been processed to find new users and
//    // users that no longer exist.
//    // therefore i will now empty the staff reception table
//    legacyStaffRepo.deleteAll();
//  }
//
//  public void publishNewJobsReallocationsAndReissues(Iterator<LegacySampleEntity> iter) {
//    while (iter.hasNext()) {
//      LegacySampleEntity legacySampleEntity = iter.next();
//      if (legacyUsersRepo.existsByAuthNo(legacySampleEntity.getAuthNo())) {
//        if (legacyJobsRepo.existsByLegacyJobId(iter.next().getLegacyJobId())) {
//          if (!executeReallocateJob(legacySampleEntity)) {
//            // TODO handle error
//          }
//        } else {
//          // may need to identify reissues in order to reference previous jobs
//          if (!executeNewJob(iter.next())) {
//            // TODO handle error
//          }
//        }
//      }
//    }
//  }
//
//  public boolean executeNewJob(LegacySampleEntity newJobEntity) {
//    LegacyUserEntity user = legacyUsersRepo.findByAuthNo(newJobEntity.getAuthNo());
//    if (user == null) {
//      throw new UnknownUserException(newJobEntity.getAuthNo());
//    }
//    String tmUsername = user.getTmUsername();
//    CreateJobRequest createJobRequest = LegacyCreateJobRequestFactory.convert(newJobEntity, tmUsername);
//    LegacyJobEntity legacyJobEntity = new LegacyJobEntity(createJobRequest);
//    legacyJobsRepo.save(legacyJobEntity);
//    try {
//      sendJobRequest(createJobRequest, "\\OPTIMISE\\INPUT");
//    } catch (Exception e) {
//      // something errored do something here
//      e.printStackTrace();
//      return false;
//    }
//
//    // Update database jobs table states for each job from inital to sent.
//    LegacyJobEntity jobEntity = legacyJobsRepo.findByTmJobId(createJobRequest.getJob().getIdentity().getReference());
//    jobEntity.setStateSent();
//    legacyJobsRepo.save(jobEntity);
//    return true;
//  }
//
//  public boolean executeReallocateJob(LegacySampleEntity reallocationEntity) {
//    String tmJobId = legacyJobsRepo.findByLegacyJobId(reallocationEntity.getLegacyJobId()).getTmJobId();
//    String tmUsername = legacyUsersRepo.findByAuthNo(reallocationEntity.getAuthNo()).getTmUsername();
//    UpdateJobHeaderRequest updateJobHeaderRequest = LegacyUpdateJobHeaderRequestFactory.reallocate(tmJobId, tmUsername);
//    try {
//      // TODO re-enable this once TM support is enabled
//      // sendUpdateJobRequest(updateJobHeaderRequest, "\\OPTIMISE\\INPUT");
//    } catch (Exception e) {
//      // TODO Auto-generated catch block
//      e.printStackTrace();
//      return false;
//    }
//
//    // Update database jobs table states for each job from inital to sent.
//    LegacyJobEntity jobEntity = legacyJobsRepo
//        .findByTmJobId(updateJobHeaderRequest.getJobHeader().getJobIdentity().getReference());
//    jobEntity.setStateSent();
//    legacyJobsRepo.save(jobEntity);
//    return true;
//  }
//
//  public void executeReissueJobs() {
//    // this may or may not be required
//  }
//
//  private <T> List<T> getAllEntities(Iterator<T> iter) {
//    List<T> entities = new ArrayList<>();
//    while (iter.hasNext()) {
//      entities.add(iter.next());
//    }
//    return entities;
//  }
//
//  private void sendJobRequest(CreateJobRequest request, String queueName) throws Exception {
//    List<SendCreateJobRequestMessage> messages = new ArrayList<>();
//
//    SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
//    message.setCreateJobRequest(request);
//
//    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
//    message.getSendMessageRequestInfo().setQueueName(queueName);
//    message.getSendMessageRequestInfo().setKey(request.getJob().getIdentity().getReference());
//    messages.add(message);
//
//    submitter.sendAll(messages);
//  }
//
//  private void sendUpdateJobRequest(UpdateJobHeaderRequest request, String queueName) throws Exception {
//    List<SendUpdateJobHeaderRequestMessage> messages = new ArrayList<>();
//
//    SendUpdateJobHeaderRequestMessage message = new SendUpdateJobHeaderRequestMessage();
//    message.setUpdateJobHeaderRequest(request);
//
//    message.setSendMessageRequestInfo(new SendMessageRequestInfo());
//    message.getSendMessageRequestInfo().setQueueName(queueName);
//    message.getSendMessageRequestInfo().setKey(request.getJobHeader().getJobIdentity().getReference());
//
//    submitter.sendAll(messages);
//  }
}
