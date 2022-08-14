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
@Table(name = "tables")
@EqualsAndHashCode(callSuper = true)
public class TableEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "brand_id")
    private Long brandId;

    private String code;
    private String name;
    private Integer status;
    public void setData(TableEntity request) {
        this.storeId = request.getStoreId();
        this.brandId = request.getBrandId();
        this.code = request.getCode();
        this.name = request.getName();
        this.status = request.getStatus();
    }
}
