package vn.anthinhphatjsc.menuzi.service.modules.chef.cookingItem;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CookingItemRequest {

    private Long processStatusId;

    private Long brandId;

    private Long storeId;

    private Long chefId;

    private Long itemId;

    private Double quantity;

    private Double quantityDoing;

    public CookingItemEntity toEntity(){
        CookingItemEntity entity = new CookingItemEntity();
        entity.setProcessStatusId(this.processStatusId);
        entity.setBrandId(this.brandId);
        entity.setStoreId(this.storeId);
        entity.setChefId(this.chefId);
        entity.setItemId(this.itemId);
        entity.setQuantity(this.quantity);
        entity.setQuantityDoing(this.quantityDoing);
        return entity;
    }
}
