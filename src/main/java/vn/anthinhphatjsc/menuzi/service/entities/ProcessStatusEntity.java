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
@Table(name = "process_status")
@EqualsAndHashCode(callSuper = true)
public class ProcessStatusEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_item_id")
    private Long orderItemId;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "item_id")
    private Long itemId;

    private Double quantity;

    private Integer status;

    public void setData(ProcessStatusEntity request) {
        this.orderItemId = request.getOrderItemId();
        this.orderId = request.getOrderId();
        this.itemId = request.getItemId();
        this.quantity = request.getQuantity();
        this.status = request.getStatus();
    }
}
