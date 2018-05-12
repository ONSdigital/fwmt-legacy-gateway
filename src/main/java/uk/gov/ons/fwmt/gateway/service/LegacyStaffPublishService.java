package uk.gov.ons.fwmt.gateway.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.entity.legacy.StaffIngest;
import uk.gov.ons.fwmt.gateway.entity.tm.TMUserEntity;
import uk.gov.ons.fwmt.gateway.repo.tm.TMUserRepo;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LegacyStaffPublishService {
  private final TMUserRepo tmUserRepo;
  private final TMService tmService;

  @Autowired
  public LegacyStaffPublishService(TMUserRepo tmUserRepo, TMService tmService) {
    this.tmUserRepo = tmUserRepo;
    this.tmService = tmService;
  }

  private static String makeTMUsername(StaffIngest staff) {
    return staff.getEmail().split("@")[0];
  }

  public void publishStaff(List<StaffIngest> staff) {
    // get a list of all the new auth numbers
    List<String> staffAuthNoList = staff.stream()
        .map(StaffIngest::getAuth)
        .collect(Collectors.toList());

    for (StaffIngest staffMember : staff) {
      if (!tmUserRepo.existsByAuthNo(staffMember.getAuth())) {
        // Staff member does not exist - they will be created
        createStaff(staffMember);
      } else if (!tmUserRepo.existsByAuthNoAndActive(staffMember.getAuth(), true)) {
        // Staff member does exist, but is not active in TM - they will be recreated
        // TODO
      }
    }

    List<String> authNosToUpdate = tmUserRepo.findByAuthNoIn(staffAuthNoList).stream()
        .map(TMUserEntity::getAuthNo)
        .collect(Collectors.toList());
    List<StaffIngest> staffToUpdate = staff.stream()
        .filter(s -> authNosToUpdate.contains(s.getAuth()))
        .collect(Collectors.toList());
    // find all of the database entries that exist in our list of auth numbers
    for (StaffIngest staffMember : staffToUpdate) {
      updateStaff(staffMember);
    }

    List<String> authNosToDeactivate = tmUserRepo.findByAuthNoNotIn(staffAuthNoList).stream()
        .map(TMUserEntity::getAuthNo)
        .collect(Collectors.toList());
    // find all of the database entries that don't exist in our list of auth numbers
    for (String authNo : authNosToDeactivate) {
      deactivateStaff(authNo);
    }
  }

  private void createStaff(StaffIngest staff) {
    // TODO
  }

  private void updateStaff(StaffIngest staff) {
    // TODO
  }

  private void deleteStaff(StaffIngest staff) {
    // TODO
  }

  private void deactivateStaff(String authNo) {
    tmUserRepo.deactivateByAuthNo(authNo);
    // TODO
  }
}
