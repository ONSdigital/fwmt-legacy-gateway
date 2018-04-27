package uk.gov.ons.fwmt.gateway.repo.monitoring;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

public interface LegacyJobsRepo extends CrudRepository<LegacyJobEntity, Long> {
    Iterable<LegacyJobEntity> findAll();
    LegacyJobEntity findByLegacyjobid(String legacyjobid);
    boolean existsByLegacyjobid(String legacyjobid);
    LegacyJobEntity findByTmjobid(String reference);
}
