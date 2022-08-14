package vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderItemRequest {
    private Long itemId;

    private Long orderId;

    private String unitName;

    private Double unitPrice;

    private Double quantity;

    private Double total;

    private String note;

    public OrderItemRequest(OrderItemRequest orderItemRequest) {
        this.itemId = orderItemRequest.getItemId();
        this.quantity = orderItemRequest.getQuantity();
        this.note = orderItemRequest.getNote();
    }

    public OrderItemEntity toEntity(){
        OrderItemEntity entity = new OrderItemEntity();
        entity.setItemId(this.itemId);
        entity.setQuantity(this.quantity);
        entity.setNote(this.note);
        return entity;
    }

}
