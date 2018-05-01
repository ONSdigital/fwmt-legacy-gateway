package uk.gov.ons.fwmt.gateway.integration_tests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.gateway.Application;
import uk.gov.ons.fwmt.gateway.entity.*;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyJobsRepo;
import uk.gov.ons.fwmt.gateway.repo.monitoring.LegacyUsersRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyLeaversRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacySampleRepo;
import uk.gov.ons.fwmt.gateway.repo.reception.LegacyStaffRepo;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RepoIT {
  @Autowired LegacyJobsRepo legacyJobRepo;
  @Autowired LegacyLeaversRepo legacyLeaversRepo;
  @Autowired LegacySampleRepo legacySampleRepo;
  @Autowired LegacyStaffRepo legacyStaffRepo;
  @Autowired LegacyUsersRepo legacyUsersRepo;

  /// Save one entity of each type to the database, then delete them
  /// We do this to test the naming of fields and the primary key functionality
  /// All IDs in this test should be placeholders that do not occur in production code
  @Test
  @Transactional
  public void storeSampleInDB() {
    LegacyJobEntity legacyJobEntity = new LegacyJobEntity();
    legacyJobEntity.setTmJobId("<test>");
    legacyJobRepo.save(legacyJobEntity);
    legacyJobRepo.delete(legacyJobEntity);

    LegacyLeaverEntity legacyLeaverEntity = new LegacyLeaverEntity();
    legacyLeaverEntity.setEmployeeNo("<no>");
    legacyLeaversRepo.save(legacyLeaverEntity);
    legacyLeaversRepo.delete(legacyLeaverEntity);

    LegacySampleEntity legacySampleEntity = new LegacySampleEntity();
    legacySampleEntity.setSerno("<no>");
    legacySampleEntity.setPrimaryKey(1L);
    legacySampleRepo.save(legacySampleEntity);
    legacySampleRepo.delete(legacySampleEntity);

    LegacyStaffEntity legacyStaffEntity = new LegacyStaffEntity();
    legacyStaffEntity.setEmployeeNo("<no>");
    legacyStaffEntity.setAuthNo("<no>");
    legacyStaffRepo.save(legacyStaffEntity);
    legacyStaffRepo.delete(legacyStaffEntity);

    LegacyUserEntity legacyUsersEntity = new LegacyUserEntity();
    legacyUsersEntity.setTmUsername("<username>");
    legacyUsersEntity.setAuthNo("<no>");
    legacyUsersRepo.save(legacyUsersEntity);
    legacyUsersRepo.delete(legacyUsersEntity);
  }
}
