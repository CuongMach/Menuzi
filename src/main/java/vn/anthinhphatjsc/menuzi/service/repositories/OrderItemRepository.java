package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItemEntity, Long>, JpaSpecificationExecutor<OrderItemEntity> {

    @Query(value = "select * from order_items where order_id = ?1 and status = 1", nativeQuery = true)
    List<OrderItemEntity> findByOrderId(Long orderID);

//    @Query(value = "select * from order_items where id = ?1 and status = 1", nativeQuery = true)
//    List<OrderItemEntity>findByIdAccept(Long id);
}
