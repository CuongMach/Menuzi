package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class ItemCategoriesResponse extends BaseResponse<ItemCategoriesDTO> {
    public ItemCategoriesResponse(Page<ItemCategoriesDTO> toPageDTO) {

        super(toPageDTO);
    }

    public ItemCategoriesResponse(List<ItemCategoriesDTO> toListDTO) {

        super(toListDTO);
    }

    public ItemCategoriesResponse(ItemCategoriesDTO toDTO) {
        super(toDTO);
    }

    public ItemCategoriesResponse() {
        super();
    }


}
