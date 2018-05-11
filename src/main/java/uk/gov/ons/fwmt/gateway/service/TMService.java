package uk.gov.ons.fwmt.gateway.service;

import org.springframework.stereotype.Service;

/**
 * This service handles all of the interactions between this gateway and TotalMobile
 */
@Service
public interface TMService {
  <I, O> O send(I message);
}
