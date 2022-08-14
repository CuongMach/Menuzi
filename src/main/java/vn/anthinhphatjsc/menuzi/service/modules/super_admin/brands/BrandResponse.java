package vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class BrandResponse extends BaseResponse<BrandDTO> {
    public BrandResponse(Page<BrandDTO> toPageDTO) {

        super(toPageDTO);
    }
    public BrandResponse(List<BrandDTO> toListDTO) {

        super(toListDTO);
    }


    public BrandResponse(BrandDTO toDTO) {
        super(toDTO);
    }

    public BrandResponse() {
        super();
    }


}
