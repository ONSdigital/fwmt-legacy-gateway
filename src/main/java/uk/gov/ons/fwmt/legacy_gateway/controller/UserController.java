package uk.gov.ons.fwmt.legacy_gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;

import java.util.List;

@Slf4j
@RestController
public class UserController {

  @Autowired
  private TMUserRepo tmUserRepo;

  @RequestMapping(value = "/activateUser", method = RequestMethod.POST, produces = "application/json")
  public void activateUser(@RequestParam("authNos") List<String> authNos) {
    authNos.stream().forEach(authNo -> {
      if (tmUserRepo.existsByAuthNo(authNo)) {
        final TMUserEntity existingTmUserEntity = tmUserRepo.findByAuthNo(authNo);
        existingTmUserEntity.setActive(true);
        tmUserRepo.save(existingTmUserEntity);
      }
    });
  }

  @RequestMapping(value = "/updateUserWithAlternateAuthNo", method = RequestMethod.POST, produces = "application/json")
  public void updateUserWithAlternateAuthNo(@RequestParam("authNo") String authNo,
      @RequestParam("alternateAuthNo") String alternateAuthNo) {
    if (tmUserRepo.existsByAuthNo(authNo)) {
      final TMUserEntity existingTmUserEntity = tmUserRepo.findByAuthNo(authNo);
      existingTmUserEntity.setAlternateAuthNo(alternateAuthNo);
      tmUserRepo.save(existingTmUserEntity);
    }

  }

  @RequestMapping(value = "/deActivateUser", method = RequestMethod.POST, produces = "application/json")
  public void deActivateUser(@RequestParam("authNos") List<String> authNos) {
    authNos.stream().forEach(authNo -> {
      if (tmUserRepo.existsByAuthNo(authNo)) {
        final TMUserEntity existingTmUserEntity = tmUserRepo.findByAuthNo(authNo);
        existingTmUserEntity.setActive(false);
        tmUserRepo.save(existingTmUserEntity);
      }
    });
  }

  @RequestMapping(value = "/listUsers", method = RequestMethod.POST, produces = "application/json")
  public List<TMUserEntity> listUsers() {
    return (List<TMUserEntity>) tmUserRepo.findAll();
  }

  @RequestMapping(value = "/createUser", method = RequestMethod.POST, produces = "application/json")
  public void createUser(@RequestParam("authNo") String authNo, @RequestParam("userName") String userName) {
    if (!tmUserRepo.existsByAuthNo(authNo)) {
      tmUserRepo.save(new TMUserEntity(authNo, userName, false, null));
    }
  }

}
