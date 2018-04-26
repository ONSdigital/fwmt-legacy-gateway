package uk.gov.ons.fwmt.gateway.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyLeaversRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacySampleRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.service.PublishService;

@Service
public class IngesterServiceImpl implements IngesterService {

    private LegacySampleRepo legacySampleRepository;
    private LegacyStaffRepo legacyStaffRepository;
    private LegacyLeaversRepo legacyLeaversRepository;
    private PublishService publishService;

    @Autowired
    public IngesterServiceImpl(LegacySampleRepo legacySampleRepository, LegacyStaffRepo legacyStaffRepository, LegacyLeaversRepo legacyLeaversRepository, PublishService publishService) {
        this.legacySampleRepository = legacySampleRepository;
        this.legacyStaffRepository = legacyStaffRepository;
        this.legacyLeaversRepository = legacyLeaversRepository;
        this.publishService = publishService;
    }

    @Override
    public void ingestLegacySample(Iterator<LegacySampleEntity> iter) {
        while (iter.hasNext()) {
            legacySampleRepository.save(iter.next());
        }
        publishService.publishNewJobsAndReallocations();
    }

    @Override
    public void ingestLegacyStaff(Iterator<LegacyStaffEntity> iter) {
        while (iter.hasNext()) {
            legacyStaffRepository.save(iter.next());
        }
        publishService.publishUpdateUsers();
    }

    // not currently required
    @Override
    public void ingestLegacyLeavers(Iterator<LegacyLeaverEntity> iter) {
        while (iter.hasNext()) {
            legacyLeaversRepository.save(iter.next());
        }
    }
}
