package uk.gov.ons.fwmt.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;
import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;
import uk.gov.ons.fwmt.gateway.service.IngesterService;
import uk.gov.ons.fwmt.gateway.service.PublishService;

import java.util.Iterator;

@Deprecated
@Service
public class IngesterServiceImpl implements IngesterService {

  private LegacyStaffRepo legacyStaffRepository;
  private PublishService publishService;

  @Autowired
  public IngesterServiceImpl(LegacyStaffRepo legacyStaffRepository,
      PublishService publishService) {
    this.legacyStaffRepository = legacyStaffRepository;
    this.publishService = publishService;
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  @Override
  public void ingestLegacySample(Iterator<LegacySampleEntity> iter) {
    publishService.publishNewJobsReallocationsAndReissues(iter);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  @Override
  public void ingestLegacyStaff(Iterator<LegacyStaffEntity> iter) {
    legacyStaffRepository.deleteAll();
    while (iter.hasNext()) {
      legacyStaffRepository.save(iter.next());
    }
    publishService.publishUpdateUsers();
  }
}
