package uk.gov.ons.fwmt.legacy_gateway.service;

public interface TMService {
  <I, O> O send(I message);
}
