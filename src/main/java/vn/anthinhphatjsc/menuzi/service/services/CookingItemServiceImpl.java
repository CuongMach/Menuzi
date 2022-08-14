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
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.CookingItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.ProcessStatusRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.CookingItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.OrderItemSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("cookingItemService")
@Transactional(rollbackFor = Throwable.class)
public class CookingItemServiceImpl implements CookingItemService{

    @Autowired
    CookingItemRepository cookingItemRepository;

    @Autowired
    ProcessStatusRepository processStatusRepository;

    @Override
    public Page<CookingItemEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<CookingItemEntity> specifications = CookingItemSpecifications.getInstance().getQueryResult(filters);

        return cookingItemRepository.findAll(specifications, pageable);
    }

    @Override
    public List<CookingItemEntity> listCookingItem(List<Filter> filters) {
        Specification<CookingItemEntity> specifications = CookingItemSpecifications.getInstance().getQueryResult(filters);
        List<CookingItemEntity> listCookingItem = cookingItemRepository.findAll(specifications);
        return listCookingItem;
    }

    @Override
    public CookingItemEntity doing(Long processStatusID,Long brandID, Long storeID, Long chefID, CookingItemEntity cookingItemEntity) throws CustomException {
        Optional<ProcessStatusEntity> optional = processStatusRepository.findById(processStatusID);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy processStatus");
        }
        List<CookingItemEntity> list = cookingItemRepository.findByProcessStatusId(optional.get().getId());
        if (list.isEmpty()){
            optional.get().setStatus(1);
            processStatusRepository.save(optional.get());
            CookingItemEntity entity = new CookingItemEntity();
            entity.setProcessStatusId(processStatusID);
            entity.setBrandId(brandID);
            entity.setStoreId(storeID);
            entity.setChefId(chefID);
            entity.setItemId(optional.get().getItemId());
            entity.setQuantity(optional.get().getQuantity());
            entity.setQuantityDoing(0D);
            cookingItemRepository.save(entity);
            return entity;
        }else
            throw new CustomException(403, "Lỗi processStatus đã tồn tại");
    }

    @Override
    public CookingItemEntity addQuantity(Long cookingItemID) throws CustomException {
        Optional<CookingItemEntity> optional = cookingItemRepository.findById(cookingItemID);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy cookingItem");
        }
        if (optional.get().getQuantity()>0){
            optional.get().setQuantityDoing(optional.get().getQuantityDoing()+1);
            optional.get().setQuantity(optional.get().getQuantity()-1);
            cookingItemRepository.save(optional.get());
        }
        return optional.get();
    }

    @Override
    public CookingItemEntity subtractQuantity(Long cookingItemID) throws CustomException {
        Optional<CookingItemEntity> optional = cookingItemRepository.findById(cookingItemID);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy cookingItem");
        }
        if (optional.get().getQuantityDoing()>0){
            optional.get().setQuantityDoing(optional.get().getQuantityDoing()-1);
            optional.get().setQuantity(optional.get().getQuantity()+1);
            cookingItemRepository.save(optional.get());
        }
        return optional.get();
    }


}
