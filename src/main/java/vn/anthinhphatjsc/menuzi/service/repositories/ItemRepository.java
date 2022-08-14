package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.Query;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long>, JpaSpecificationExecutor<ItemEntity> {


}
