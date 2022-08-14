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
@Table(name = "invoices")
public class InvoiceEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "brand_id")
    private Long brandId;

    private String customer_name;

    private String customer_phone;

    private String company_name;

    private String company_tax_code;

    private String company_phone;

    private String company_email;

    private String company_address;

    @Column(name = "total_all")
    private Double totalAll;

    public void setData(InvoiceEntity request) {
        this.company_name = request.getCompany_name();
        this.company_tax_code = request.getCompany_tax_code();
        this.company_phone = request.getCompany_phone();
        this.company_email = request.getCompany_email();
        this.company_address = request.getCompany_address();
    }
}
