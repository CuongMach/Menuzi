package vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class CookingOrderResponse extends BaseResponse<CookingOrderDTO> {
    public CookingOrderResponse(Page<CookingOrderDTO> toPageDTO) {

        super(toPageDTO);
    }
    public CookingOrderResponse(List<CookingOrderDTO> toListDTO) {

        super(toListDTO);
    }

    public CookingOrderResponse(CookingOrderDTO toDTO) {
        super(toDTO);
    }

    public CookingOrderResponse() {
        super();
    }
}
