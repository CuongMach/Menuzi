package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;

import java.util.List;

@Repository
public interface CookingOrderRepository extends JpaRepository<CookingOrderEntity, Long>, JpaSpecificationExecutor<CookingOrderEntity> {

    @Query(value = "select * from cooking_orders where cooking_item_id = ?1", nativeQuery = true)
    List<CookingOrderEntity> findByCookingItemId(Long cookingItemID);
}
