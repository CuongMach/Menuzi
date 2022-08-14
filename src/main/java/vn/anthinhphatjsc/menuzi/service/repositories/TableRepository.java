package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;

@Repository
public interface TableRepository extends JpaRepository<TableEntity, Long>, JpaSpecificationExecutor<TableEntity> {

}
