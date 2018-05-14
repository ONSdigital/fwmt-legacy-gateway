package uk.gov.ons.fwmt.legacy_gateway.integration_tests.Repo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import uk.gov.ons.fwmt.legacy_gateway.Application;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMJobEntity;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMJobRepo;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;

import javax.transaction.Transactional;

import static junit.framework.TestCase.fail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
public class RepoIntegrationTest {
  @Autowired private TMUserRepo tmUserRepo;
  @Autowired private TMJobRepo tmJobRepo;

  @Test
  @Transactional
  public void storeUserInDb() {
    TMUserEntity tmUserEntity = new TMUserEntity("9999", "testuser", false);
    try {
      tmUserEntity.getAuthNo();
      tmUserEntity.getTmUsername();
      tmUserEntity.setActive(true);
      tmUserRepo.save(tmUserEntity);
    } catch (Exception e){
      fail("error");
    } finally {
      tmUserRepo.delete(tmUserEntity);
    }
  }

  @Test
  @Transactional
  public void storeJobInDb() {
    TMJobEntity tmJobEntity = new TMJobEntity("testjob");
    try {
      tmJobEntity.getTmJobId();
      tmJobRepo.save(tmJobEntity);
    } catch (Exception e) {
      fail("Error");
    } finally {
      tmJobRepo.delete(tmJobEntity);
    }
  }
}
