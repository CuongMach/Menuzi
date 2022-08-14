package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface BrandService {

    Page<BrandEntity> paginate(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    BrandEntity save(BrandEntity brandEntity) throws CustomException;

    BrandEntity delete(Long id) throws CustomException;


    BrandEntity update(Long id, BrandEntity brandEntity) throws CustomException;

    List<BrandEntity> listBrand(List<Filter> whereParams);
}