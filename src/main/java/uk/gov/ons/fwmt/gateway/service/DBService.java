package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;

/**
 * This service handles all of the interactions between us and our database
 */
@Service
public interface DBService {
  void registerUser();
  boolean doesUserExist();

  void registerJob();
  boolean doesJobExist();
}
