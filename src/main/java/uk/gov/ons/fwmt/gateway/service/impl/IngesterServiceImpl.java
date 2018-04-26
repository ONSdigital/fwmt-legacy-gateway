package uk.gov.ons.fwmt.gateway.service.impl;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyLeaversRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacySampleRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;
import uk.gov.ons.fwmt.gateway.service.IngesterService;

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
    public int ingestLegacySample(Iterator<LegacySampleEntity> iter) {
        int count = 0;
        while (iter.hasNext()) {
            legacySampleRepository.save(iter.next());
            count++;
        }
        return count;
    }

    @Override
    public int ingestLegacyStaff(Iterator<LegacyStaffEntity> iter) {
      int count = 0;
      legacyStaffRepository.deleteAll();
      while (iter.hasNext()) {
        legacyStaffRepository.save(iter.next());
        count++;
      }
      return count;
    }
}
