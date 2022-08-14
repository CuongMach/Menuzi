package vn.anthinhphatjsc.menuzi.service.entities;


import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseEntity;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cooking_items")
@EqualsAndHashCode(callSuper = true)
public class CookingItemEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "process_status_id")
    private Long processStatusId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "chef_id")
    private Long chefId;

    @Column(name = "item_id")
    private Long itemId;

    private Double quantity;

    private Double quantityDoing;

    public void setData(CookingItemEntity request) {
        this.processStatusId = request.getProcessStatusId();
        this.brandId = request.getBrandId();
        this.storeId = request.getStoreId();
        this.chefId = request.getChefId();
        this.itemId = request.getItemId();
        this.quantity = request.getQuantity();
        this.quantityDoing = request.getQuantityDoing();
    }
}
