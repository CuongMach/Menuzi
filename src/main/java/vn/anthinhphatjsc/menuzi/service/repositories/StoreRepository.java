package vn.anthinhphatjsc.menuzi.service.repositories;

import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<StoreEntity, Long>, JpaSpecificationExecutor<StoreEntity> {

    Optional<StoreEntity> findByName(String id);
}
