package vn.anthinhphatjsc.menuzi.service.modules.chef.cookingItem;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class CookingItemResponse extends BaseResponse<CookingItemDTO> {
    public CookingItemResponse(Page<CookingItemDTO> toPageDTO) {

        super(toPageDTO);
    }
    public CookingItemResponse(List<CookingItemDTO> toListDTO) {

        super(toListDTO);
    }

    public CookingItemResponse(CookingItemDTO toDTO) {
        super(toDTO);
    }

    public CookingItemResponse() {
        super();
    }
}
