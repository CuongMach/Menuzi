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
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.CookingItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.CookingOrderRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.ProcessStatusRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.CookingItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.CookingOrderSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("cookingOrderService")
@Transactional(rollbackFor = Throwable.class)
public class CookingOrderServiceImpl implements CookingOrderService{

    @Autowired
    CookingOrderRepository cookingOrderRepository;

    @Autowired
    CookingItemRepository cookingItemRepository;

    @Autowired
    ProcessStatusRepository processStatusRepository;

    @Override
    public Page<CookingOrderEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<CookingOrderEntity> specifications = CookingOrderSpecifications.getInstance().getQueryResult(filters);

        return cookingOrderRepository.findAll(specifications, pageable);
    }


    @Override
    public List<CookingOrderEntity> listCookingOrder(Long cookingItemID, List<Filter> filters) {
        List<CookingOrderEntity> listCookingOrder = cookingOrderRepository.findByCookingItemId(cookingItemID);
        return listCookingOrder;
    }

    @Override
    public CookingOrderEntity done(Long cookingItemID, CookingOrderEntity cookingOrderEntity) throws CustomException {
        Optional<CookingItemEntity> optional = cookingItemRepository.findById(cookingItemID);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy cookingItem");
        }
        Optional<ProcessStatusEntity> processStatusEntityOptional = processStatusRepository.findById(optional.get().getProcessStatusId());
        if (processStatusEntityOptional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy processStatus");
        }
            processStatusEntityOptional.get().setStatus(2);
            processStatusRepository.save(processStatusEntityOptional.get());
            CookingOrderEntity entity = new CookingOrderEntity();
            entity.setCookingItemId(optional.get().getId());
            entity.setItemId(optional.get().getItemId());
            entity.setOrderItemId(processStatusEntityOptional.get().getOrderItemId());
            cookingOrderRepository.save(entity);
            cookingItemRepository.deleteById(cookingItemID);
            return entity;
    }
}
