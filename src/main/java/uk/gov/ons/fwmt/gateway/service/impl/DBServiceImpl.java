package uk.gov.ons.fwmt.gateway.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.ons.fwmt.gateway.service.DBService;

@Service
public class DBServiceImpl implements DBService {
  @Autowired

  @Override public void registerUser() {

  }

  @Override public boolean doesUserExist() {
    return false;
  }

  @Override public void registerJob() {

  }

  @Override public boolean doesJobExist() {
    return false;
  }
}
