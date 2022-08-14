package vn.anthinhphatjsc.menuzi.service.entities;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseEntity;

import javax.persistence.*;
import java.util.Random;

@Table(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@EqualsAndHashCode(callSuper = true)
public class OrderEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "table_id")
    private Long tableId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "brand_id")
    private Long brandId;

    private String code;

    private Double total;

    private Integer status;

    private String customer_name;

    private String customer_phone;

    public void setData(OrderEntity request) {
        this.tableId = request.getTableId();
        this.total = request.getTotal();
        this.status = request.getStatus();
        this.customer_name = request.getCustomer_name();
        this.customer_phone = request.customer_phone;
    }


    public OrderEntity (Long id, OrderEntity request) {
        this.id = id;
        this.tableId = request.getTableId();
        this.storeId = request.getStoreId();
        this.brandId = request.getBrandId();
        this.code = request.getCode();
        this.total = request.getTotal();
        this.status = request.getStatus();
        this.customer_name = request.getCustomer_name();
        this.customer_phone = request.customer_phone;
    }

}
