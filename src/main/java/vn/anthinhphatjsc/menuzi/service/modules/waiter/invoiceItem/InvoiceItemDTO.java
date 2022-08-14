package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoiceItem;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;

import javax.persistence.Column;
import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceItemDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long invoiceId;

//    private Long orderItemId;

//    private Long orderId;

    private String unitName;

    private Double unitPrice;

    private Double quantity;

    private Double total;

//    private Double totalAll;
}
