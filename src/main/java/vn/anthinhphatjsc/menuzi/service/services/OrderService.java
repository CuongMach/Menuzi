package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem.OrderItemRequest;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Page<OrderEntity> paginate(Long tableId, Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<OrderEntity> listOrder(Long tableId, List<Filter> whereParams) throws CustomException;

    OrderEntity save(OrderEntity orderEntity, Long storeID, Long brandID, List<OrderItemRequest> orderItemEntityList) throws CustomException;

    OrderEntity delete(Long id) throws CustomException;


    OrderEntity update(Long id, OrderEntity orderEntity) throws CustomException;



}
