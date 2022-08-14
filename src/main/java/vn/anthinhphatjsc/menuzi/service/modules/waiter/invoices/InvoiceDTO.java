package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices;

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
public class InvoiceDTO extends BaseDTO implements Serializable {
    private Long id;

    private Long orderId;

    private Long storeId;

    private Long brandId;

    private String customer_name;

    private String customer_phone;

    private String company_name;

    private String company_tax_code;

    private String company_phone;

    private String company_email;

    private String company_address;

    private Double totalAll;
}
