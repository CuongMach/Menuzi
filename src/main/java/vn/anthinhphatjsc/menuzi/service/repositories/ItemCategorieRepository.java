package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.Query;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

import java.util.Optional;

@Repository
public interface ItemCategorieRepository extends JpaRepository<ItemCategoryEntity, Long>, JpaSpecificationExecutor<ItemCategoryEntity> {
    @Query(value = "SELECT * from item_categories where id = ?1",nativeQuery = true )
    Optional<ItemCategoryEntity> findCategoryById(Long id);
}
