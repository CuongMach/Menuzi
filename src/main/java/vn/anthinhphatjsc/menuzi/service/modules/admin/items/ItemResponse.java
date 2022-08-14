package vn.anthinhphatjsc.menuzi.service.modules.admin.items;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class ItemResponse extends BaseResponse<ItemDTO> {
    public ItemResponse(Page<ItemDTO> toPageDTO) {

        super(toPageDTO);
    }
    public ItemResponse(List<ItemDTO> toListDTO) {

        super(toListDTO);
    }

    public ItemResponse(ItemDTO toDTO) {
        super(toDTO);
    }

    public ItemResponse() {
        super();
    }


}
