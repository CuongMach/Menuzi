package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;

import java.util.List;

@Repository
public interface ProcessStatusRepository extends JpaRepository<ProcessStatusEntity, Long>, JpaSpecificationExecutor<ProcessStatusEntity> {

    @Query(value = "select * from process_status p join order_items oi on oi.order_id = p.order_id join orders o on o.id = oi.order_id where oi.status = 1", nativeQuery = true)
    List<ProcessStatusEntity> findByOrderId(Long orderID);

    @Query(value = "select * from process_status where order_item_id = ?1", nativeQuery = true)
    List<ProcessStatusEntity>findByOrderItemIdAccept(Long orderItemID);

    @Query(value = "select * from process_status where order_item_id = ?1", nativeQuery = true)
    List<ProcessStatusEntity>findByOrderItemIdCancel(Long orderItemID);
}