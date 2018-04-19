package uk.gov.ons.fwmt.gateway.service.impl;

import com.consiliumtechnologies.schemas.mobile._2015._05.optimisemessages.CreateJobRequest;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessage;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendCreateJobRequestMessageResponse;
import com.consiliumtechnologies.schemas.services.mobile._2009._03.messaging.SendMessageRequestInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.utility.TMMessageSubmitter;
import uk.gov.ons.fwmt.gateway.utility.csvconverter.LegacyCreateJobRequestFactory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IngesterServiceImpl implements IngesterService {

    private LegacySampleRepo legacySampleRepository;
    private LegacyStaffRepo legacyStaffRepository;
    private LegacyLeaversRepo legacyLeaversRepository;

    @Autowired
    public IngesterServiceImpl(LegacySampleRepo legacySampleRepository, LegacyStaffRepo legacyStaffRepository, LegacyLeaversRepo legacyLeaversRepository) {
        this.legacySampleRepository = legacySampleRepository;
        this.legacyStaffRepository = legacyStaffRepository;
        this.legacyLeaversRepository = legacyLeaversRepository;
    }

    @Override
    public void ingestLegacySample(Iterator<LegacySampleEntity> iter) {
        while (iter.hasNext()) {
            legacySampleRepository.save(iter.next());
        }
    }

    @Override
    public void ingestLegacyStaff(Iterator<LegacyStaffEntity> iter) {
        legacyStaffRepository.deleteAll();
        while (iter.hasNext()) {
            legacyStaffRepository.save(iter.next());
        }
    }

    @Override
    public void ingestLegacyLeavers(Iterator<LegacyLeaverEntity> iter) {
        while (iter.hasNext()) {
            legacyLeaversRepository.save(iter.next());
        }
    }
}
