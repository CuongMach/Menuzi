package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import java.util.List;
import java.util.Map;

public interface CookingOrderService {
    Page<CookingOrderEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

    List<CookingOrderEntity> listCookingOrder(Long cookingItemID, List<Filter> filters);

    CookingOrderEntity done(Long cookingItemID, CookingOrderEntity cookingOrderEntity) throws CustomException;
}
