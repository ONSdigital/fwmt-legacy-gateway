package uk.gov.ons.fwmt.legacy_gateway.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uk.gov.ons.fwmt.legacy_gateway.entity.TMUserEntity;

import java.util.List;

@Repository
public interface TMUserRepo extends CrudRepository<TMUserEntity, Long> {
  List<TMUserEntity> findByActive(boolean active);
  List<TMUserEntity> findByAuthNoIn(List<String> authNoList);
  List<TMUserEntity> findByAuthNoNotIn(List<String> authNoList);
  boolean existsByAuthNo(String authNo);
  boolean existsByAuthNoAndActive(String authNo, boolean active);
  default void deactivateByAuthNo(String authNo) {
    // TODO
  }
}
