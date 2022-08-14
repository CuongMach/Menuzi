package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.BrandRepository;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.ItemCategorieRepository;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.repositories.ItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.StoreRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.ItemSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.*;

@Service("itemService")
@Transactional(rollbackFor = Throwable.class)
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;
    @Autowired
    ItemCategorieRepository itemCategorieRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<ItemEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {

        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của storeID");
        }
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));

        Specification<ItemEntity> specifications = ItemSpecifications.getInstance().getQueryResult(filters);

        return itemRepository.findAll(specifications, pageable);
    }

    @Override
    public List<ItemEntity> listItemAdmin(Long storeID, List<Filter> filters) throws CustomException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của storeID");
        }
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<ItemEntity> specifications = ItemSpecifications.getInstance().getQueryResult(filters);
        return itemRepository.findAll(specifications);
    }

    @Override
    public ItemEntity saveAdmin(ItemEntity itemEntity, Long storeId) throws CustomException {
        ItemEntity userEntity = new ItemEntity();
        Optional<ItemCategoryEntity> itemCategorieEntity = itemCategorieRepository.findById(itemEntity.getCategoriesId());
        if(itemCategorieEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy categorie");
        }
        Long idRole = AuthUtil.getInstance().getCurrentUser().getRoleId();
        System.out.println(idRole);
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của storeID");
        }
        userEntity.setData(itemEntity);
        userEntity.setStoreId(storeId);
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return itemRepository.save(userEntity);
    }

    @Override
    public ItemEntity deleteAdmin(Long id, Long storeId) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<ItemEntity> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của item ");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể xóa item của thương hiệu khác");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(403, "Lỗi không trùng id của store");
        }
        ItemEntity entity = itemRepository.getById(id);
        itemRepository.delete(entity);
        return entity;

    }


    @Override
    public ItemEntity updateAdmin(Long id, ItemEntity itemEntity, Long storeId) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<ItemEntity> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của item");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể sửa item của thương hiệu khác");
        }
        Optional<ItemCategoryEntity> itemCategorieEntity = itemCategorieRepository.findById(itemEntity.getCategoriesId());
        if(itemCategorieEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy categorie");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(itemEntity.getStoreId());
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(403, "Lỗi không trùng id của store");
        }
        ItemEntity userEntity = optional.get();
        userEntity.setData(itemEntity);
        userEntity.setStoreId(storeId);
        userEntity.setBrandId(brandId);
        return itemRepository.save(userEntity);
    }

    @Override
    public Page<ItemEntity> paginateManager(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Long storeID = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandID = AuthUtil.getInstance().getCurrentUser().getBrandId();
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandID.toString(), null));
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));

        Specification<ItemEntity> specifications = ItemSpecifications.getInstance().getQueryResult(filters);

        return itemRepository.findAll(specifications, pageable);
    }

    @Override
    public List<ItemEntity> listItemManager(List<Filter> filters) throws CustomException {
        Long storeID = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandID = AuthUtil.getInstance().getCurrentUser().getBrandId();
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandID.toString(), null));
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<ItemEntity> specifications = ItemSpecifications.getInstance().getQueryResult(filters);
        return itemRepository.findAll(specifications);
    }

    @Override
    public ItemEntity saveManager(ItemEntity itemEntity) throws CustomException {
        ItemEntity userEntity = new ItemEntity();
        Optional<ItemCategoryEntity> itemCategorieEntity = itemCategorieRepository.findById(itemEntity.getCategoriesId());
        if(itemCategorieEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy categorie");
        }
        userEntity.setData(itemEntity);
        userEntity.setStoreId(AuthUtil.getInstance().getCurrentUser().getStoreId());
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return itemRepository.save(userEntity);
    }

    @Override
    public ItemEntity deleteManager(Long id) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Optional<ItemEntity> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của item ");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể xóa item của thương hiệu khác");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(401, "Lỗi bạn không thể xóa item của cửa hàng khác");
        }
        ItemEntity entity = itemRepository.getById(id);
        itemRepository.delete(entity);
        return entity;
    }

    @Override
    public ItemEntity updateManager(Long id, ItemEntity itemEntity) throws CustomException {
        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<ItemEntity> optional = itemRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của item");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(401, "Lỗi bạn không thể sửa item của cửa hàng khác");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(401, "Lỗi bạn không thể sửa item của thương hiệu khác");
        }
        Optional<ItemCategoryEntity> itemCategorieEntity = itemCategorieRepository.findById(itemEntity.getCategoriesId());
        if(itemCategorieEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy categorie");
        }
        ItemEntity userEntity = optional.get();
        userEntity.setData(itemEntity);
        userEntity.setStoreId(storeId);
        userEntity.setBrandId(brandId);
        return itemRepository.save(userEntity);
    }



}