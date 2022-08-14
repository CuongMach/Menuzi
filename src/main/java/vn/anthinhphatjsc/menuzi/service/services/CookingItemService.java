package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import java.util.List;
import java.util.Map;

public interface CookingItemService {
    Page<CookingItemEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

    List<CookingItemEntity> listCookingItem(List<Filter> filters);

    CookingItemEntity doing(Long processStatusID,Long brandID, Long storeID, Long chefID, CookingItemEntity cookingItemEntity) throws CustomException;

    CookingItemEntity addQuantity(Long cookingItemID) throws CustomException;

    CookingItemEntity subtractQuantity(Long cookingItemID) throws CustomException;

}
