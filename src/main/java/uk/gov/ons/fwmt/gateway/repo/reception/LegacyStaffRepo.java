package uk.gov.ons.fwmt.gateway.repo.reception;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacyStaffEntity;

public interface LegacyStaffRepo extends CrudRepository<LegacyStaffEntity, Long> {
    Iterable<LegacyStaffEntity> findAll();
    boolean existsByAuthno(String authno);
}
