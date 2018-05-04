package uk.gov.ons.fwmt.gateway.repo.reception;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacySampleEntity;

public interface LegacySampleRepo extends CrudRepository<LegacySampleEntity, Long> {
    Iterable<LegacySampleEntity> findAll();
    // TODO should be by composite PK
    void deleteByLegacyJobId(String legacyJobId);
}
