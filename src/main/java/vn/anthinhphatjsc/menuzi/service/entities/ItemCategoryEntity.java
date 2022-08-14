package vn.anthinhphatjsc.menuzi.service.entities;

import vn.anthinhphatjsc.menuzi.service.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item_categories")
@EqualsAndHashCode(callSuper = true)
public class ItemCategoryEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "brand_id")
    private Long brandId;

    private String name;

    private String image;

    public void setData(ItemCategoryEntity request) {
        this.storeId = request.getStoreId();
        this.brandId = request.getBrandId();
        this.name = request.getName();
        this.image = request.getImage();
    }
}
