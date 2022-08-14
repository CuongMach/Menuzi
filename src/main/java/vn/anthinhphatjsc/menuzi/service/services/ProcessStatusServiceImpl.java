package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.OrderItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.ProcessStatusRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.ProcessStatusSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.StoreSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("processStatusService")
@Transactional(rollbackFor = Throwable.class)
public class ProcessStatusServiceImpl implements ProcessStatusService{

    @Autowired
    ProcessStatusRepository processStatusRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Page<ProcessStatusEntity> paginate(Long orderID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        filters.add(new Filter("orderID", QueryOperator.EQUALS, orderID.toString(), null));

        Specification<ProcessStatusEntity> specifications = ProcessStatusSpecifications.getInstance().getQueryResult(filters);

        return processStatusRepository.findAll(specifications, pageable);
    }

    @Override
    public List<ProcessStatusEntity> listProcessStatus(Long orderID, List<Filter> whereParams) throws CustomException {
        List<ProcessStatusEntity> processStatusEntityList = processStatusRepository.findByOrderId(orderID);
        return processStatusEntityList;
    }

    @Override
    public ProcessStatusEntity save( Long orderID,ProcessStatusEntity orderEntity) throws CustomException {
        return null;
    }

    @Override
    public ProcessStatusEntity delete(Long id) throws CustomException {
        return null;
    }

    @Override
    public ProcessStatusEntity update(Long id, ProcessStatusEntity processStatusEntity) throws CustomException {
        return null;
    }

    @Override
    public ProcessStatusEntity accept(Long orderItemID, ProcessStatusEntity entity) throws CustomException {
        Optional<OrderItemEntity> orderItemEntity = orderItemRepository.findById(orderItemID);
        if (orderItemEntity.isEmpty()){
            throw new CustomException(403, "Lỗi không tìm thấy id của orderItem");
        }
        List<ProcessStatusEntity> list = processStatusRepository.findByOrderItemIdAccept(orderItemEntity.get().getId());
        if (list.isEmpty()){
            orderItemEntity.get().setStatus(1);
            orderItemRepository.save(orderItemEntity.get());
            ProcessStatusEntity processStatusEntity = new ProcessStatusEntity();
            processStatusEntity.setItemId(orderItemEntity.get().getItemId());
            processStatusEntity.setOrderId(orderItemEntity.get().getOrderId());
            processStatusEntity.setOrderItemId(orderItemEntity.get().getId());
            processStatusEntity.setQuantity(orderItemEntity.get().getQuantity());
            processStatusEntity.setStatus(0);
            processStatusRepository.save(processStatusEntity);
            return processStatusEntity;
        }else{
            throw new CustomException(403, "Lỗi orderItem đã tồn tại");
        }
    }

    @Override
    public ProcessStatusEntity cancel(Long orderItemID,ProcessStatusEntity entity) throws CustomException {
        Optional<OrderItemEntity> orderItemEntity = orderItemRepository.findById(orderItemID);
        if (orderItemEntity.isEmpty()){
            throw new CustomException(403, "Lỗi không tìm thấy id của orderItem");
        }
        List<ProcessStatusEntity> list = processStatusRepository.findByOrderItemIdCancel(orderItemEntity.get().getId());
        if (list.isEmpty()){
            orderItemEntity.get().setStatus(2);
            orderItemRepository.deleteById(orderItemID);
            ProcessStatusEntity processStatusEntity = new ProcessStatusEntity();
            return processStatusEntity;
        }else{
            throw new CustomException(403, "Lỗi orderItem đã bị hủy");
        }
    }
}
