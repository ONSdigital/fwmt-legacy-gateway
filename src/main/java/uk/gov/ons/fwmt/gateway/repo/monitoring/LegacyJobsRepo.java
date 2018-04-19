package uk.gov.ons.fwmt.gateway.repo.monitoring;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

public interface LegacyJobsRepo extends CrudRepository<LegacyJobEntity, Long> {
    Iterable<LegacyJobEntity> findAll();
    LegacyJobEntity findBySerno(String serno);
    boolean existsBySerno(String serno);
    LegacyJobEntity findByTmjobid(String reference);
}
