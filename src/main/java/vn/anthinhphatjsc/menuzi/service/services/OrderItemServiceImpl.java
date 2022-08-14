package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.*;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.ItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.OrderItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.OrderRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.ProcessStatusRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.BrandSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.OrderItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.RoleSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("orderItemService")
@Transactional(rollbackFor = Throwable.class)
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ProcessStatusRepository processStatusRepository;

    @Override
    public Page<OrderItemEntity> paginate(Long orderID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<OrderItemEntity> specifications = OrderItemSpecifications.getInstance().getQueryResult(filters);

        return orderItemRepository.findAll(specifications, pageable);
    }

    @Override
    public OrderItemEntity delete(Long id) throws CustomException {
        Optional<OrderItemEntity> optional = orderItemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy orderItem ");
        }
        OrderItemEntity entity = orderItemRepository.getById(id);
        orderItemRepository.delete(entity);
        return entity;
    }

    @Override
    public OrderItemEntity update(Long id, OrderItemEntity orderItemEntity) throws CustomException {
        Optional<OrderItemEntity> optional = orderItemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy orderItem");
        }
        Optional<ItemEntity> optionalItemEntity = itemRepository.findById(orderItemEntity.getItemId());
        if (optionalItemEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy item");
        }
        OrderItemEntity entity = optional.get();
        entity.setData(orderItemEntity);
        entity.setTotal(optionalItemEntity.get().getUnitPrice() * orderItemEntity.getQuantity());
        return orderItemRepository.save(entity);
    }

    @Override
    public List<OrderItemEntity> listOrderItem(Long orderID, List<Filter> filters) {
        List<OrderItemEntity> listOrderItem = orderItemRepository.findByOrderId(orderID);
        return listOrderItem;
    }

//    @Override
//    public OrderItemEntity accept(Long id, OrderItemEntity entity) throws CustomException {
//        Optional<OrderItemEntity> orderItemEntity = orderItemRepository.findById(id);
//        if (orderItemEntity.isEmpty()){
//            throw new CustomException(403, "Lỗi không tìm thấy id của orderItem");
//        }
//        List<OrderItemEntity> list = orderItemRepository.findByIdAccept(id);
//        if (list.isEmpty()){
//            orderItemEntity.get().setStatus(1);
//            orderItemRepository.save(orderItemEntity.get());
//            ProcessStatusEntity processStatusEntity = new ProcessStatusEntity();
//            processStatusEntity.setItemId(orderItemEntity.get().getItemId());
//            processStatusEntity.setOrderId(orderItemEntity.get().getOrderId());
//            processStatusEntity.setOrderItemId(orderItemEntity.get().getId());
//            processStatusEntity.setQuantity(orderItemEntity.get().getQuantity());
//            processStatusEntity.setStatus(0);
//            processStatusRepository.save(processStatusEntity);
//            return orderItemRepository.save(entity);
//        }else{
//            throw new CustomException(403, "Lỗi orderItem đã tồn tại");
//        }
//    }
}
