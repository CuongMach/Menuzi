package vn.anthinhphatjsc.menuzi.service.modules.manager.itemCategories;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ItemCategorieResponse extends BaseResponse<ItemCategorieDTO> {
    public ItemCategorieResponse(Page<ItemCategorieDTO> toPageDTO) {

        super(toPageDTO);
    }

    public ItemCategorieResponse(List<ItemCategorieDTO> toListDTO) {

        super(toListDTO);
    }

    public ItemCategorieResponse(ItemCategorieDTO toDTO) {
        super(toDTO);
    }

    public ItemCategorieResponse() {
        super();
    }


}
