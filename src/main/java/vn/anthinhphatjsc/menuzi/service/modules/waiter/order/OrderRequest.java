package vn.anthinhphatjsc.menuzi.service.modules.waiter.order;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem.OrderItemRequest;

import java.util.ArrayList;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderRequest {
    private Long tableId;
    private Integer status;
    private String customer_name;
    private String customer_phone;

    private OrderItemRequest[] orderItemRequestList;

    public List<OrderItemRequest> toListItems() {
        List<OrderItemRequest> orderItemRequestList1 = new ArrayList<>();
        for (int i = 0; i <this.orderItemRequestList.length; i++) {
            orderItemRequestList1.add(new OrderItemRequest(orderItemRequestList[i]));
        }
        return  orderItemRequestList1;
    }

    public OrderEntity toEntity(){
        OrderEntity entity = new OrderEntity();
        entity.setTableId(this.tableId);
        entity.setStatus(this.status);
        entity.setCustomer_name(this.customer_name);
        entity.setCustomer_phone(this.customer_phone);
        return entity;
    }


}
