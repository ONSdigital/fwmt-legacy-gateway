package uk.gov.ons.fwmt.gateway.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.ons.fwmt.gateway.entity.TMJobEntity;

@Repository
public interface TMJobRepo extends CrudRepository<TMJobEntity, Long> {
}
