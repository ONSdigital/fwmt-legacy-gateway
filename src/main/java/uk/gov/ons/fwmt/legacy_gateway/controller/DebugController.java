/*
  Copyright.. etc
 */

package uk.gov.ons.fwmt.legacy_gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;
import uk.gov.ons.fwmt.legacy_gateway.repo.TMUserRepo;

/**
 * A class for manually triggering various debugging actions
 *
 * @author Thomas Poot
 * @author James Berry
 * @author Jacob Harrison
 */

@Slf4j
@Controller
@Profile({"dev", "test"})
public class DebugController {
  @Autowired
  private TMUserRepo tmUserRepo;

  @PostMapping("/debug/clearUsers")
  public void clearUsers(RedirectAttributes redirectAttributes) {
    tmUserRepo.deleteAll();
  }

  @PostMapping("/debug/setupUsers")
  public void setupUsers(RedirectAttributes redirectAttributes) {
  }
}
