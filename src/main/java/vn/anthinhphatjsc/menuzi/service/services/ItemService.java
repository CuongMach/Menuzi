package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemService {

    Page<ItemEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<ItemEntity> listItemAdmin(Long storeID, List<Filter> whereParams) throws CustomException;

    ItemEntity saveAdmin(ItemEntity itemEntity, Long storeId) throws CustomException;

    ItemEntity deleteAdmin(Long id, Long storeId) throws CustomException;

    ItemEntity updateAdmin(Long id, ItemEntity itemEntity, Long storeId) throws CustomException;

    Page<ItemEntity> paginateManager( Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<ItemEntity> listItemManager(List<Filter> whereParams) throws CustomException;

    ItemEntity saveManager(ItemEntity itemEntity) throws CustomException;

    ItemEntity deleteManager(Long id) throws CustomException;

    ItemEntity updateManager(Long id, ItemEntity itemEntity) throws CustomException;



}