package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.BrandRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.BrandSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("brandService")
@Transactional(rollbackFor = Throwable.class)
public class BrandServiceImpl implements BrandService {

    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<BrandEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
//        if (page == null || limit == null){
//            Pageable pageable = PageRequest.of();
//        }
        Pageable pageable = PageRequest.of(page, limit, sort);


        Specification<BrandEntity> specifications = BrandSpecifications.getInstance().getQueryResult(filters);

        return brandRepository.findAll(specifications, pageable);
    }

    @Override
    public BrandEntity save(BrandEntity brandEntity) throws CustomException {
        BrandEntity userEntity = new BrandEntity();
        userEntity.setData(brandEntity);
        return brandRepository.save(userEntity);
    }

    @Override
    public BrandEntity delete(Long id) throws CustomException {
        Optional<BrandEntity> optional = brandRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy thương hiệu ");
        }
        BrandEntity entity = brandRepository.getById(id);
        brandRepository.delete(entity);
        return entity;

    }


    @Override
    public BrandEntity update(Long id, BrandEntity brandEntity) throws CustomException {
        Optional<BrandEntity> optional = brandRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy thương hiệu");
        }
        BrandEntity userEntity = optional.get();
        userEntity.setData(brandEntity);
        return brandRepository.save(userEntity);
    }

    @Override
    public List<BrandEntity> listBrand(List<Filter> filters) {
        Specification<BrandEntity> specifications = BrandSpecifications.getInstance().getQueryResult(filters);
        List<BrandEntity> listBrandEntities = brandRepository.findAll(specifications);
        return listBrandEntities;
    }

}