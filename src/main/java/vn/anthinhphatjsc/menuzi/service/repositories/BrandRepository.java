package vn.anthinhphatjsc.menuzi.service.repositories;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long>, JpaSpecificationExecutor<BrandEntity> {

    Optional<BrandEntity> findByName(String id);
}
