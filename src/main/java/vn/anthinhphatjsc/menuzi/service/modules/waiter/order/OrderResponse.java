package vn.anthinhphatjsc.menuzi.service.modules.waiter.order;

import org.springframework.data.domain.Page;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class OrderResponse extends BaseResponse<OrderDTO> {
    public OrderResponse(Page<OrderDTO> toPageDTO) {

        super(toPageDTO);
    }
    public OrderResponse(List<OrderDTO> toListDTO) {

        super(toListDTO);
    }

    public OrderResponse(OrderDTO toDTO) {
        super(toDTO);
    }

    public OrderResponse() {
        super();
    }
}
