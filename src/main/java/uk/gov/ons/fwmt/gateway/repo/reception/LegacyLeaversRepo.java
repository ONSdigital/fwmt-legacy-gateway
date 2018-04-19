package uk.gov.ons.fwmt.gateway.repo.reception;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacyLeaverEntity;

public interface LegacyLeaversRepo extends CrudRepository<LegacyLeaverEntity, Long> {
    Iterable<LegacyLeaverEntity> findAll();
}
