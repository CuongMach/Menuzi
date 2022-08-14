package vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.order.OrderDTO;

import java.util.List;

public class OrderItemResponse extends BaseResponse<OrderItemDTO> {
    public OrderItemResponse(Page<OrderItemDTO> toPageDTO) {

        super(toPageDTO);
    }
    public OrderItemResponse(List<OrderItemDTO> toListDTO) {

        super(toListDTO);
    }

    public OrderItemResponse(OrderItemDTO toDTO) {
        super(toDTO);
    }

    public OrderItemResponse() {
        super();
    }
}
