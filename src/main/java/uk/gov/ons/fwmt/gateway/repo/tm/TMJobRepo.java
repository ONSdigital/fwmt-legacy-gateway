package uk.gov.ons.fwmt.gateway.repo.tm;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.ons.fwmt.gateway.entity.tm.TMJobEntity;

@Repository
public interface TMJobRepo extends CrudRepository<TMJobEntity, Long> {
}
