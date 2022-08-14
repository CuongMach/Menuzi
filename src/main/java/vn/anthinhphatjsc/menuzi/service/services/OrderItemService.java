package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import java.util.List;
import java.util.Map;

public interface OrderItemService {

    Page<OrderItemEntity> paginate(Long orderID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

//    OrderItemEntity save(Long orderID, OrderItemEntity orderItemEntity) throws CustomException;

    OrderItemEntity delete(Long id) throws CustomException;


    OrderItemEntity update(Long id, OrderItemEntity brandEntity) throws CustomException;

    List<OrderItemEntity> listOrderItem(Long orderID, List<Filter> filters);

//    OrderItemEntity accept(Long id, OrderItemEntity entity) throws CustomException;


}