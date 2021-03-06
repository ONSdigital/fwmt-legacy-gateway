package uk.gov.ons.fwmt.legacy_gateway.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.legacy_gateway.data.legacy_ingest.LegacyStaffIngest;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;
import uk.gov.ons.fwmt.legacy_gateway.service.LegacyStaffPublishService;
import uk.gov.ons.fwmt.legacy_gateway.service.TMService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LegacyStaffPublishServiceImpl implements LegacyStaffPublishService {
  private final TMUserRepo tmUserRepo;

  @Autowired
  public LegacyStaffPublishServiceImpl(TMUserRepo tmUserRepo) {
    this.tmUserRepo = tmUserRepo;
  }

  public void publishStaff(List<LegacyStaffIngest> staff) {
    // get a list of all the new auth numbers
    List<String> staffAuthNoList = staff.stream()
        .map(LegacyStaffIngest::getAuth)
        .collect(Collectors.toList());

    for (LegacyStaffIngest staffMember : staff) {
      if (!tmUserRepo.existsByAuthNo(staffMember.getAuth())) {
        // Staff member does not exist - they will be created
        createStaff(staffMember);
      } else if (!tmUserRepo.existsByAuthNoAndActive(staffMember.getAuth(), true)) {
        // Staff member does exist, but is not active in TM - they will be
        // recreated
        // TODO
      }
    }

    List<String> authNosToUpdate = tmUserRepo.findByAuthNoIn(staffAuthNoList).stream()
        .map(TMUserEntity::getAuthNo)
        .collect(Collectors.toList());
    List<LegacyStaffIngest> staffToUpdate = staff.stream()
        .filter(s -> authNosToUpdate.contains(s.getAuth()))
        .collect(Collectors.toList());
    // find all of the database entries that exist in our list of auth numbers
    for (LegacyStaffIngest staffMember : staffToUpdate) {
      updateStaff(staffMember);
    }

    List<String> authNosToDeactivate = tmUserRepo.findByAuthNoNotIn(staffAuthNoList).stream()
        .map(TMUserEntity::getAuthNo)
        .collect(Collectors.toList());
    // find all of the database entries that don't exist in our list of auth
    // numbers
    for (String authNo : authNosToDeactivate) {
      deactivateStaff(authNo);
    }
  }

  private void createStaff(LegacyStaffIngest staff) {
    TMUserEntity tmUserEntity = new TMUserEntity(staff.getAuth(), getTmUsername(staff.getEmail()), false, null);
    tmUserRepo.save(tmUserEntity);
  }

  private void updateStaff(LegacyStaffIngest staff) {
    // TODO
  }

  private void deleteStaff(LegacyStaffIngest staff) {
    // TODO
  }

  private void deactivateStaff(String authNo) {
    tmUserRepo.deactivateByAuthNo(authNo);
    // TODO
  }

  private String getTmUsername(String email) {
    return email.split("@")[0];
  }
}
