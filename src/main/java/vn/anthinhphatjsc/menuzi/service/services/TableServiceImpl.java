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
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.*;
import vn.anthinhphatjsc.menuzi.service.specifications.ItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.TableSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.*;

@Service("tableService")
@Transactional(rollbackFor = Throwable.class)
public class TableServiceImpl implements TableService {

    @Autowired
    TableRepository tableRepository;
    @Autowired
    ItemCategorieRepository itemCategorieRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    BrandRepository brandRepository;

    @Override
    public Page<TableEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {

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

        Specification<TableEntity> specifications = TableSpecifications.getInstance().getQueryResult(filters);

        return tableRepository.findAll(specifications, pageable);
    }

    @Override
    public List<TableEntity> listAdmin(Long storeID, List<Filter> filters) throws CustomException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeID);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của storeID");
        }
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<TableEntity> specifications = TableSpecifications.getInstance().getQueryResult(filters);
        return tableRepository.findAll(specifications);
    }

    @Override
    public TableEntity saveAdmin(TableEntity tableEntity, Long storeId) throws CustomException {

        Long idRole = AuthUtil.getInstance().getCurrentUser().getRoleId();
        System.out.println(idRole);
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của storeID");
        }
        TableEntity entity = new TableEntity();
        entity.setData(tableEntity);
        entity.setStoreId(storeId);
        entity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return tableRepository.save(entity);
    }

    @Override
    public TableEntity deleteAdmin(Long id, Long storeId) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<TableEntity> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của table ");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể xóa item của thương hiệu khác");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId);
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(403, "Lỗi không trùng id của store");
        }
        TableEntity entity = tableRepository.getById(id);
        tableRepository.delete(entity);
        return entity;

    }


    @Override
    public TableEntity updateAdmin(Long id, TableEntity itemEntity, Long storeId) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<TableEntity> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của table");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể sửa item của thương hiệu khác");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(itemEntity.getStoreId());
        if(storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(403, "Lỗi không trùng id của store");
        }
        TableEntity entity = optional.get();
        entity.setData(itemEntity);
        entity.setStoreId(storeId);
        entity.setBrandId(brandId);
        return tableRepository.save(entity);
    }

    @Override
    public Page<TableEntity> paginateManager(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
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

        Specification<TableEntity> specifications = TableSpecifications.getInstance().getQueryResult(filters);

        return tableRepository.findAll(specifications, pageable);
    }

    @Override
    public List<TableEntity> listManager(List<Filter> filters) throws CustomException {
        Long storeID = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandID = AuthUtil.getInstance().getCurrentUser().getBrandId();
        System.out.println(storeID);
        System.out.println(brandID);
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandID.toString(), null));
        filters.add(new Filter("storeId", QueryOperator.EQUALS, storeID.toString(), null));
        Specification<TableEntity> specifications = TableSpecifications.getInstance().getQueryResult(filters);
        return tableRepository.findAll(specifications);
    }

    @Override
    public TableEntity saveManager(TableEntity tableEntity) throws CustomException {
        TableEntity entity = new TableEntity();
        entity.setData(tableEntity);
        entity.setStoreId(AuthUtil.getInstance().getCurrentUser().getStoreId());
        entity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return tableRepository.save(entity);
    }

    @Override
    public TableEntity deleteManager(Long id) throws CustomException {
        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<TableEntity> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của table ");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(403, "Lỗi bạn không thể xóa item của thương hiệu khác");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(401, "Lỗi bạn không thể xóa item của cửa hàng khác");
        }
        TableEntity entity = tableRepository.getById(id);
        tableRepository.delete(entity);
        return entity;
    }

    @Override
    public TableEntity updateManager(Long id, TableEntity itemEntity) throws CustomException {
        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<TableEntity> optional = tableRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của table");
        }else if (!Objects.equals(optional.get().getStoreId(), storeId)){
            throw new CustomException(401, "Lỗi bạn không thể sửa item của cửa hàng khác");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(401, "Lỗi bạn không thể sửa item của thương hiệu khác");
        }
        TableEntity entity = optional.get();
        entity.setData(itemEntity);
        entity.setStoreId(AuthUtil.getInstance().getCurrentUser().getStoreId());
        entity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        return tableRepository.save(entity);
    }

    @Override
    public Page<TableEntity> paginateWaiter(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
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
        filters.add(new Filter("status", QueryOperator.EQUALS, "0", null));
        Specification<TableEntity> specifications = TableSpecifications.getInstance().getQueryResult(filters);

        return tableRepository.findAll(specifications, pageable);
    }

}