package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoiceItem;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceItemRequest {
    private Long invoiceId;

    private String unitName;

    private Double unitPrice;

    private Double quantity;

    private Double total;

    private Double totalAll;

    public InvoiceItemEntity toEntity() {
        InvoiceItemEntity entity = new InvoiceItemEntity();
        entity.setInvoiceId(this.invoiceId);
        entity.setUnitName(this.unitName);
        entity.setUnitPrice(this.unitPrice);
        entity.setQuantity(this.quantity);
        entity.setTotal(this.total);
//        entity.setTotalAll(this.totalAll);
        return entity;
    }

}
