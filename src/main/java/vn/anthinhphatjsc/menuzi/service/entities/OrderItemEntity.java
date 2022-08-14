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
@Table(name = "order_items")
@EqualsAndHashCode(callSuper = true)
public class OrderItemEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "item_id", unique = true)
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "category_name")
    private String categoriesName;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_price")
    private Double unitPrice;

    private Double quantity;

    private Double total;

    private String note;

    private Integer status;

    public void setData(OrderItemEntity request){
        this.itemId = request.getItemId();
        this.quantity = request.getQuantity();
        this.note = request.getNote();
    }

    public OrderItemEntity(Long orderId){
        this.orderId = orderId;
    }

}
