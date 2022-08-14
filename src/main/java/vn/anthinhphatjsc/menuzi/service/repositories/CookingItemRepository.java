package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;

import java.util.List;

@Repository
public interface CookingItemRepository extends JpaRepository<CookingItemEntity, Long>, JpaSpecificationExecutor<CookingItemEntity> {

    @Query(value = "select * from cooking_items where process_status_id = ?1 and status = 1", nativeQuery = true)
    List<CookingItemEntity> findByProcessStatusId(Long processStatusID);
}
