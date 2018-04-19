package uk.gov.ons.fwmt.gateway.repo.monitoring;

import org.springframework.data.repository.CrudRepository;

import uk.gov.ons.fwmt.gateway.entity.LegacyUserEntity;

public interface LegacyUsersRepo extends CrudRepository<LegacyUserEntity, Long> {
    Iterable<LegacyUserEntity> findAll();
    LegacyUserEntity findByAuthNo(String authNo);
    boolean existsByAuthNo(String authNo);
    LegacyUserEntity findByTmusername(String tmUsername);
    boolean existsByTmusername(String proposedTMUsername);
}
