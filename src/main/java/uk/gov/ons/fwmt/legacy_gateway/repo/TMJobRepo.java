package uk.gov.ons.fwmt.legacy_gateway.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMJobEntity;

@Repository
public interface TMJobRepo extends CrudRepository<TMJobEntity, Long> {
  boolean existsByTmJobId(String tmJobId);
  boolean existsByTmJobIdAndLastAuthNo(String tmJobId, String lastAuthNo);
  TMJobEntity findByTmJobId(String tmJobId);
}
