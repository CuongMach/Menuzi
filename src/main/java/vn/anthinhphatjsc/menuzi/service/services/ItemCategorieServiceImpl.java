package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.BrandRepository;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.ItemCategorieRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.StoreRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.ItemCategorieSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.specifications.ItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.*;

@Service("itemCategoriesService")
@Transactional(rollbackFor = Throwable.class)
public class ItemCategorieServiceImpl implements ItemCategorieService {

    @Autowired
    ItemCategorieRepository itemCategorieRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<ItemCategoryEntity> paginateManager(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
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

        Specification<ItemCategoryEntity> specifications = ItemCategorieSpecifications.getInstance().getQueryResult(filters);

        return itemCategorieRepository.findAll(specifications, pageable);
    }

    @Override
    public List<ItemCategoryEntity> listItemCategorieManager(List<Filter> filters) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();

        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));

        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();

        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeId.toString(), null));
        Specification<ItemCategoryEntity> specifications = ItemCategorieSpecifications.getInstance().getQueryResult(filters);
        return itemCategorieRepository.findAll(specifications);
    }

    @Override
    public ItemCategoryEntity saveManager(ItemCategoryEntity itemCategoryEntity) throws CustomException {
        ItemCategoryEntity userEntity = new ItemCategoryEntity();
        userEntity.setData(itemCategoryEntity);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        if (userEntity.getStoreId() != null) {
            userEntity.setStoreId(userEntity.getStoreId());
        } else {
            userEntity.setStoreId(AuthUtil.getInstance().getCurrentUser().getStoreId());
        }
        return itemCategorieRepository.save(userEntity);
    }

    @Override
    public ItemCategoryEntity deleteManager(Long id) throws CustomException {
        Optional<ItemCategoryEntity> optional = itemCategorieRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a th??? lo???i ");
        }
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        if (!Objects.equals(optional.get().getBrandId(), brandId)) {
            throw new CustomException(401, "L???i b???n kh??ng th??? x??a v?? th??? lo???i n??y kh??ng thu???c th????ng hi???u c???a b???n");
        }
        ItemCategoryEntity entity = itemCategorieRepository.getById(id);
        itemCategorieRepository.delete(entity);
        return entity;

    }


    @Override
    public ItemCategoryEntity updateManager(Long id, ItemCategoryEntity itemCategoryEntity) throws CustomException {
        Optional<ItemCategoryEntity> optional = itemCategorieRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a th??? lo???i");
        }
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        if (!Objects.equals(optional.get().getBrandId(), brandId)) {
            throw new CustomException(401, "L???i b???n kh??ng th??? s???a v?? th??? lo???i n??y kh??ng thu???c th????ng hi???u c???a b???n ");
        }
        ItemCategoryEntity userEntity = optional.get();
        userEntity.setData(itemCategoryEntity);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        if (userEntity.getStoreId() != null) {
            userEntity.setStoreId(userEntity.getStoreId());
        } else {
            userEntity.setStoreId(AuthUtil.getInstance().getCurrentUser().getStoreId());
        }
        return itemCategorieRepository.save(userEntity);
    }

    @Override
    public Page<ItemCategoryEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a storeID");
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<ItemCategoryEntity> specifications = ItemCategorieSpecifications.getInstance().getQueryResult(filters);

        return itemCategorieRepository.findAll(specifications, pageable);
    }

    @Override
    public List<ItemCategoryEntity> listItemCategorieAdmin(Long storeID, List<Filter> filters) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a storeID");
        }
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<ItemCategoryEntity> specifications = ItemCategorieSpecifications.getInstance().getQueryResult(filters);
        return itemCategorieRepository.findAll(specifications);
    }

    @Override
    public ItemCategoryEntity saveAdmin(Long storeID, ItemCategoryEntity itemCategoryEntity) throws CustomException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a storeID");
        }
        ItemCategoryEntity userEntity = new ItemCategoryEntity();
        userEntity.setData(itemCategoryEntity);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        userEntity.setStoreId(storeID);
        return itemCategorieRepository.save(userEntity);
    }

    @Override
    public ItemCategoryEntity deleteAdmin(Long storeID, Long id) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<ItemCategoryEntity> optional = itemCategorieRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a th??? lo???i ");
        } else if (!Objects.equals(optional.get().getBrandId(), brandId)) {
            throw new CustomException(403, "L???i b???n kh??ng th??? x??a th??? lo???i c???a th????ng hi???u kh??c");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y store");
        } else if (!Objects.equals(optional.get().getStoreId(), storeID)) {
            throw new CustomException(403, "L???i kh??ng tr??ng id c???a store");
        }
        ItemCategoryEntity entity = itemCategorieRepository.getById(id);
        itemCategorieRepository.delete(entity);
        return entity;
    }

    @Override
    public ItemCategoryEntity updateAdmin(Long storeID, Long id, ItemCategoryEntity itemCategoryEntity) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<ItemCategoryEntity> optional = itemCategorieRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y id c???a th??? lo???i ");
        } else if (!Objects.equals(optional.get().getBrandId(), brandId)) {
            throw new CustomException(403, "L???i b???n kh??ng th??? s???a th??? lo???i c???a th????ng hi???u kh??c");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "L???i kh??ng t??m th???y store");
        } else if (!Objects.equals(optional.get().getStoreId(), storeID)) {
            throw new CustomException(403, "L???i kh??ng tr??ng id c???a store");
        }
        ItemCategoryEntity userEntity = new ItemCategoryEntity();
        userEntity.setData(itemCategoryEntity);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        userEntity.setStoreId(storeID);
        return itemCategorieRepository.save(userEntity);
    }

    @Override
    public Optional<ItemCategoryEntity> findCategoryById(Long id) throws CustomException {
        return itemCategorieRepository.findCategoryById(id);
    }

}