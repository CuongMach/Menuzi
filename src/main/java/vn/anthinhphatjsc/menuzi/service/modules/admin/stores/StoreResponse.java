package vn.anthinhphatjsc.menuzi.service.modules.admin.stores;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandDTO;

import java.util.List;

public class StoreResponse extends BaseResponse<StoreDTO> {
    public StoreResponse(Page<StoreDTO> toPageDTO) {

        super(toPageDTO);
    }
    public StoreResponse(List<StoreDTO> toListDTO) {

        super(toListDTO);
    }

    public StoreResponse(StoreDTO toDTO) {
        super(toDTO);
    }

    public StoreResponse() {
        super();
    }


}
