package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class InvoiceRequest {
    private String company_name;

    private String company_tax_code;

    private String company_phone;

    private String company_email;

    private String company_address;

    public InvoiceEntity toEntity() {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setCompany_name(this.company_name);
        entity.setCompany_tax_code(this.company_tax_code);
        entity.setCompany_phone(this.company_phone);
        entity.setCompany_email(this.company_email);
        entity.setCompany_address(this.company_address);
        return entity;
    }

}
