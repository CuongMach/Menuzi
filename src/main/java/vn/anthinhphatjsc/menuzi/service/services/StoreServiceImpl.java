package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.BrandRepository;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.StoreRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.BrandSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.StoreSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("storeService")
@Transactional(rollbackFor = Throwable.class)
public class StoreServiceImpl implements StoreService {

    @Autowired
    StoreRepository storeRepository;
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<StoreEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        System.out.println(brandId);
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));

        Specification<StoreEntity> specifications = StoreSpecifications.getInstance().getQueryResult(filters);

        return storeRepository.findAll(specifications, pageable);
    }

    @Override
    public StoreEntity save(StoreEntity storeEntity) throws CustomException {
        StoreEntity userEntity = new StoreEntity();
        userEntity.setData(storeEntity);
        return storeRepository.save(userEntity);
    }

    @Override
    public StoreEntity delete(Long id) throws CustomException {
        Optional<StoreEntity> optional = storeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của store ");
        }
        StoreEntity entity = storeRepository.getById(id);
        storeRepository.delete(entity);
        return entity;

    }


    @Override
    public StoreEntity update(Long id, StoreEntity storeEntity) throws CustomException {
        Optional<StoreEntity> optional = storeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của store");
        }
        StoreEntity userEntity = optional.get();
        userEntity.setData(storeEntity);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return storeRepository.save(userEntity);
    }

    @Override
    public List<StoreEntity> listStore(List<Filter> filters) {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        System.out.println(brandId);
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));
        Specification<StoreEntity> specifications = StoreSpecifications.getInstance().getQueryResult(filters);
        List<StoreEntity> listBrandEntities = storeRepository.findAll(specifications);
        return listBrandEntities;
    }

}