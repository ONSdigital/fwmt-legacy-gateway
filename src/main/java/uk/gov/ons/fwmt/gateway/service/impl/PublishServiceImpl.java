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
    private LegacySampleRepo legacySampleRepo;
    private LegacyStaffRepo legacyStaffRepo;
    private LegacyLeaversRepo legacyLeaversRepo;
    private LegacyJobsRepo legacyJobsRepo;
    private LegacyUsersRepo legacyUsersRepo;

    private List<LegacySampleEntity> sampleEntries;
    private List<LegacyStaffEntity> staffEntries;
    private List<LegacyLeaverEntity> leaversEntries;
    private List<LegacyJobEntity> jobsEntries;
    private List<LegacyUserEntity> usersEntries;

    @Autowired
    public PublishServiceImpl(TMMessageSubmitter submitter, LegacySampleRepo legacySampleRepo,
            LegacyStaffRepo legacyStaffRepo, LegacyLeaversRepo legacyLeaversRepo, LegacyJobsRepo legacyJobsRepo,
            LegacyUsersRepo legacyUsersRepo) {
        this.submitter = submitter;
        this.legacySampleRepo = legacySampleRepo;
        this.legacyStaffRepo = legacyStaffRepo;
        this.legacyLeaversRepo = legacyLeaversRepo;
        this.legacyJobsRepo = legacyJobsRepo;
        this.legacyUsersRepo = legacyUsersRepo;
    }

    public void populateEntryArrays() {
        sampleEntries = new ArrayList<>();
        legacySampleRepo.findAll().forEach(sampleEntries::add);

        staffEntries = new ArrayList<>();
        legacyStaffRepo.findAll().forEach(staffEntries::add);

        leaversEntries = new ArrayList<>();
        legacyLeaversRepo.findAll().forEach(leaversEntries::add);

        jobsEntries = new ArrayList<>();
        legacyJobsRepo.findAll().forEach(jobsEntries::add);

        usersEntries = new ArrayList<>();
        legacyUsersRepo.findAll().forEach(usersEntries::add);
    }

    @Override
    public void execute() {
        populateEntryArrays();
        executeUpdateUsers();
        executeJobs();
    }

    public void executeUpdateUsers() {
        List<LegacyStaffEntity> newStaffEntities = new ArrayList<LegacyStaffEntity>();
        List<LegacyUserEntity> removedUserEntities = new ArrayList<LegacyUserEntity>();

        for (LegacyUserEntity user : usersEntries) {
            boolean removedUser = true;
            for (LegacyStaffEntity staff : staffEntries) {
                if (user.getAuthNo().equals(staff.getAuthno())) {
                    removedUser = false;
                }
            }
            if (removedUser) {
                removedUserEntities.add(user);
            }
        }
        
        for (LegacyStaffEntity staff : staffEntries) {
            boolean newStaff = true;
            for (LegacyUserEntity user : usersEntries) {
                if (user.getAuthNo().equals(staff.getAuthno())) {
                    newStaff = false;
                }
            }
            if (newStaff) {
                newStaffEntities.add(staff);
            }
            
            legacyStaffRepo.delete(staff);
        }

        for (LegacyUserEntity removedUser : removedUserEntities) {
            removedUser.setAuthNo(null);
            legacyUsersRepo.save(removedUser);
        }

        for (LegacyStaffEntity newStaff : newStaffEntities) {
            // find if staff member already exists
            // if so then update thier staffid
            if (legacyUsersRepo.existsByTmusername(getProposedTMUsername(newStaff.getEmail()))) {
                LegacyUserEntity user = legacyUsersRepo.findByTmusername(getProposedTMUsername(newStaff.getEmail()));
                user.setAuthNo(newStaff.getAuthno());
                legacyUsersRepo.save(user);
            } else {
                // if not then create new user entity
                LegacyUserEntity newUser = LegacyUsersReader.createUserEntity(newStaff,
                        getProposedTMUsername(newStaff.getEmail()));
                // create new mobilise user in TM

                // TODO CREATE THE USER IN TM USING THE ADMINWS OR SELENIUM
                System.out.println("Must create new user "+newUser.getTmusername() + " and set correct AuthNo.");
                // make sure to add authority number as an additional property

                // add to the users table
                legacyUsersRepo.save(newUser);
            }
        }
    }

    @Override
    public void executeJobs() {
        List<LegacySampleEntity> newJobsEntities = new ArrayList<LegacySampleEntity>();
        List<LegacySampleEntity> reallocationEntities = new ArrayList<LegacySampleEntity>();        
        
        for (LegacySampleEntity sample : sampleEntries) {
            if (legacyJobsRepo.existsBySerno(sample.getSerno())) {
                reallocationEntities.add(sample);
            } else {
                newJobsEntities.add(sample);
            }
        }

        executeAllocationJobs(newJobsEntities);
        executeReallocateJobs(reallocationEntities);
    }

    public void executeAllocationJobs(List<LegacySampleEntity> newJobsEntities) {
        List<CreateJobRequest> createJobRequests = LegacyCreateJobRequestFactory.convert(sampleEntries, newJobsEntities,
                usersEntries);
        for (CreateJobRequest createJobRequest : createJobRequests) {
            LegacyJobEntity legacyJobEntity = LegacyJobsReader.createJobEntity(createJobRequest);
            legacyJobsRepo.save(legacyJobEntity);
        }
        sendJobRequests(createJobRequests, "\\OPTIMISE\\INPUT");
        
        for(LegacySampleEntity processedSample: newJobsEntities) {
            legacySampleRepo.delete(processedSample);
        }

        // Update database jobs table states for each job from inital to sent.
        for (CreateJobRequest job : createJobRequests) {
            LegacyJobEntity jobEntity = legacyJobsRepo.findByTmjobid(job.getJob().getIdentity().getReference());
            legacyJobsRepo.save(LegacyJobsReader.setStateSent(jobEntity));
        }
    }

    public void executeReallocateJobs(List<LegacySampleEntity> reallocationEntities) {
        List<UpdateJobHeaderRequest> updateJobHeaderRequest = new ArrayList<UpdateJobHeaderRequest>();
        for (LegacySampleEntity entity : reallocationEntities) {
            String tmJobId = legacyJobsRepo.findBySerno(entity.getSerno()).getTmjobid();
            updateJobHeaderRequest
                    .add(LegacyUpdateJobHeaderRequestFactory.reallocate(tmJobId, entity.getAuth(), usersEntries));
        }

        sendUpdateJobRequests(updateJobHeaderRequest, "\\OPTIMISE\\INPUT");
        
        for(LegacySampleEntity processedSample: reallocationEntities) {
            legacySampleRepo.delete(processedSample);
        }
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

    private void sendJobRequests(List<CreateJobRequest> requests, String queueName) {
        List<SendCreateJobRequestMessage> messages = requests.stream().map(r -> {
            SendCreateJobRequestMessage message = new SendCreateJobRequestMessage();
            message.setCreateJobRequest(r);

            message.setSendMessageRequestInfo(new SendMessageRequestInfo());
            message.getSendMessageRequestInfo().setQueueName(queueName);
            message.getSendMessageRequestInfo().setKey(r.getJob().getIdentity().getReference());

            return message;
        }).collect(Collectors.toList());

        submitter.sendAll(messages);
    }

    private void sendUpdateJobRequests(List<UpdateJobHeaderRequest> requests, String queueName) {
        List<SendUpdateJobHeaderRequestMessage> messages = requests.stream().map(r -> {
            SendUpdateJobHeaderRequestMessage message = new SendUpdateJobHeaderRequestMessage();
            message.setUpdateJobHeaderRequest(r);

            message.setSendMessageRequestInfo(new SendMessageRequestInfo());
            message.getSendMessageRequestInfo().setQueueName(queueName);
            message.getSendMessageRequestInfo().setKey(r.getJobHeader().getJobIdentity().getReference());

            return message;
        }).collect(Collectors.toList());

        submitter.sendAll(messages);
    }

    private String getProposedTMUsername(String email) {
        String username = email.split("@")[0];
        return username;
    }
}
