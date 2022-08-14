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
@Table(name = "invoice_items")
public class InvoiceItemEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "invoice_id")
    private Long invoiceId;

//    @Column(name = "order_item_id")
//    private Long orderItemId;

    @Column(name = "unit_name")
    private String unitName;

    @Column(name = "unit_price")
    private Double unitPrice;

    private Double quantity;

    private Double total;

//    @Column(name = "total_all")
//    private Double totalAll;

    public void setData(InvoiceItemEntity request) {
        this.invoiceId = request.getInvoiceId();
        this.unitName = request.getUnitName();
        this.unitPrice = request.getUnitPrice();
        this.quantity = request.getQuantity();
        this.total = request.getTotal();
//        this.totalAll = request.getTotalAll();
    }
}
