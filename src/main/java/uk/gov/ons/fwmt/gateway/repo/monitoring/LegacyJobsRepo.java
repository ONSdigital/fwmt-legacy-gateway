package uk.gov.ons.fwmt.gateway.repo.monitoring;

import org.springframework.data.repository.CrudRepository;
import uk.gov.ons.fwmt.gateway.entity.LegacyJobEntity;

@Deprecated
public interface LegacyJobsRepo extends CrudRepository<LegacyJobEntity, Long> {
    Iterable<LegacyJobEntity> findAll();
    LegacyJobEntity findByLegacyJobId(String legacyjobid);
    boolean existsByLegacyJobId(String legacyjobid);
    LegacyJobEntity findByTmJobId(String reference);
}
