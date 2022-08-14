package vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CookingOrderRequest {

    private Long cookingItemId;

    private Long itemId;

    private Long orderItemId;

    public CookingOrderEntity toEntity(){
        CookingOrderEntity entity = new CookingOrderEntity();
        entity.setCookingItemId(this.cookingItemId);
        entity.setItemId(this.itemId);
        entity.setOrderItemId(this.orderItemId);
        return entity;
    }
}
