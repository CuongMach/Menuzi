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
@Table(name = "cooking_orders")
@EqualsAndHashCode(callSuper = true)
public class CookingOrderEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "cooking_item_id")
    private Long cookingItemId;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_item_id")
    private Long orderItemId;

    public void setData(CookingOrderEntity request) {
        this.cookingItemId = request.getCookingItemId();
        this.itemId = request.getItemId();
        this.orderItemId = request.getOrderItemId();
    }

}
