package vn.anthinhphatjsc.menuzi.service.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.*;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem.OrderItemRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.order.OrderRequest;
import vn.anthinhphatjsc.menuzi.service.repositories.*;
import vn.anthinhphatjsc.menuzi.service.specifications.OrderSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import javax.validation.Valid;
import java.util.*;

@Service("orderService")
@Transactional(rollbackFor = Throwable.class)
public class OrderServiceImpl implements OrderService {
    private final String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuiopasdfghjklzxcvbnm";
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemCategorieRepository itemCategorieRepository;

    @Override
    public Page<OrderEntity> paginate(Long tableId, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();

        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));

        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();

        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeId.toString(), null));

        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);

        return orderRepository.findAll(specifications, pageable);
    }


    @Override
    public List<OrderEntity> listOrder(Long tableId, List<Filter> filters) throws CustomException {
        Optional<TableEntity> tableEntity = tableRepository.findById(tableId);
        if (tableEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của tableId");
        }
        filters.add(new Filter("tableId", QueryOperator.EQUALS, tableId.toString(), null));
        Specification<OrderEntity> specifications = OrderSpecifications.getInstance().getQueryResult(filters);
        return orderRepository.findAll(specifications);
    }


    @Override
    public OrderEntity save(OrderEntity orderEntity, Long storeID, Long brandID, List<OrderItemRequest> orderItemEntityList) throws CustomException {
        Optional<TableEntity> tableEntity = tableRepository.findById(orderEntity.getTableId());
        if (tableEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của order");
        }

        Optional<OrderEntity> list = orderRepository.findByTableID(tableEntity.get().getId());
        if (list.isEmpty()) {
            OrderEntity orderEntity1 = new OrderEntity();
            orderEntity1.setCode(RandomStringUtils.random(10, input));
            orderEntity1.setStoreId(storeID);
            orderEntity1.setBrandId(brandID);
            orderEntity1.setData(orderEntity);
            orderEntity1.setTotal(0D);
            orderRepository.save(orderEntity1);
            if (orderEntity1.getStatus() == 1) {
                for (int i = 0; i < orderItemEntityList.size(); i++) {
                    OrderItemEntity orderItemEntity = new OrderItemEntity();
                    orderItemEntity.setOrderId(orderEntity1.getId());
                    orderItemEntity.setItemId(orderItemEntityList.get(i).getItemId());
                    orderItemEntity.setNote(orderItemEntityList.get(i).getNote());
                    orderItemEntity.setQuantity(orderItemEntityList.get(i).getQuantity());
                    Optional<ItemEntity> optionalItemEntity = itemRepository.findById(orderItemEntityList.get(i).getItemId());
                    orderItemEntity.setUnitName(optionalItemEntity.get().getUnitName());
                    orderItemEntity.setUnitPrice(optionalItemEntity.get().getUnitPrice());
                    orderItemEntity.setStatus(0);
                    Optional<ItemCategoryEntity> optionalItemCategoryEntity = itemCategorieRepository.findById(optionalItemEntity.get().getCategoriesId());
                    orderItemEntity.setCategoriesName(optionalItemCategoryEntity.get().getName());
                    orderItemEntity.setTotal(optionalItemEntity.get().getUnitPrice() * orderItemEntityList.get(i).getQuantity());
                    orderItemRepository.save(orderItemEntity);
                    List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderId(orderEntity1.getId());
                    double total = 0;
                    for (int j = 0; j < orderItemEntities.size(); j++) {
                        total = total + orderItemEntities.get(j).getTotal();
                    }
                    orderEntity1.setTotal(total);
                    orderRepository.save(orderEntity1);
                }
            }
            return orderEntity1;
        } else {
            throw new CustomException(403, "Table đã được xác nhận");
        }
    }

    @Override
    public OrderEntity delete(Long id) throws CustomException {
        Optional<OrderEntity> optional = orderRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của order");
        }
        OrderEntity entity = orderRepository.getById(id);
        orderRepository.delete(entity);
        return entity;
    }

    @Override
    public OrderEntity update(Long id, OrderEntity orderEntity) throws CustomException {
        Optional<OrderEntity> optional = orderRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của order");
        }
        OrderEntity entity = optional.get();
        entity.setTableId(orderEntity.getTableId());
        entity.setCustomer_name(orderEntity.getCustomer_name());
        entity.setCustomer_phone(orderEntity.getCustomer_phone());
        entity.setData(orderEntity);
        entity.setTotal(orderEntity.getTotal());
        return orderRepository.save(entity);
    }
}
