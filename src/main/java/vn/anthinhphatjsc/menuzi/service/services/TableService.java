package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import java.util.List;
import java.util.Map;

public interface TableService {

    Page<TableEntity> paginateAdmin(Long storeID, Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<TableEntity> listAdmin(Long storeID, List<Filter> whereParams) throws CustomException;

    TableEntity saveAdmin(TableEntity itemEntity, Long storeId) throws CustomException;

    TableEntity deleteAdmin(Long id, Long storeId) throws CustomException;

    TableEntity updateAdmin(Long id, TableEntity itemEntity, Long storeId) throws CustomException;

    Page<TableEntity> paginateManager( Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<TableEntity> listManager( List<Filter> whereParams) throws CustomException;

    TableEntity saveManager(TableEntity itemEntity) throws CustomException;

    TableEntity deleteManager(Long id) throws CustomException;

    TableEntity updateManager(Long id, TableEntity itemEntity) throws CustomException;

    Page<TableEntity> paginateWaiter( Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;


}