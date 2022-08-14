package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemCategorieService {

    Page<ItemCategoryEntity> paginateManager(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<ItemCategoryEntity> listItemCategorieManager(List<Filter> whereParams) throws CustomException;

    ItemCategoryEntity saveManager(ItemCategoryEntity itemCategoryEntity) throws CustomException;

    ItemCategoryEntity deleteManager(Long id) throws CustomException;

    ItemCategoryEntity updateManager(Long id, ItemCategoryEntity itemCategoryEntity) throws CustomException;

    Page<ItemCategoryEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<ItemCategoryEntity> listItemCategorieAdmin(Long storeID,List<Filter> whereParams) throws CustomException;

    ItemCategoryEntity saveAdmin(Long storeID,ItemCategoryEntity itemCategoryEntity) throws CustomException;

    ItemCategoryEntity deleteAdmin(Long storeID,Long id) throws CustomException;

    ItemCategoryEntity updateAdmin(Long storeID,Long id, ItemCategoryEntity itemCategoryEntity) throws CustomException;

    Optional<ItemCategoryEntity> findCategoryById(Long id) throws CustomException;

}