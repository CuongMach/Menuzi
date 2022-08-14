package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface StoreService {

    Page<StoreEntity> paginate(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    StoreEntity save(StoreEntity storeEntity) throws CustomException;

    StoreEntity delete(Long id) throws CustomException;


    StoreEntity update(Long id, StoreEntity storeEntity) throws CustomException;

    List<StoreEntity> listStore(List<Filter> whereParams);
}