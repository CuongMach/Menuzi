package vn.anthinhphatjsc.menuzi.service.entities;

import lombok.extern.java.Log;
import vn.anthinhphatjsc.menuzi.service.core.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
@EqualsAndHashCode(callSuper = true)
public class ItemEntity extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @Column(name = "categorie_id")
    private Long categoriesId;

    @Column(name = "store_id")
    private Long storeId;

    @Column(name = "brand_id")
    private Long brandId;

    @Column(name = "unit_name")
    private String unitName;
    @Column(name = "unit_price")
    private Double unitPrice;
    private String image;
    @Column(name = "video_url")
    private String videoUrl;
    private String description;
    private Integer status;

    public void setData(ItemEntity request) {
        this.categoriesId = request.getCategoriesId();
        this.storeId = request.getStoreId();
        this.brandId = request.getBrandId();
        this.unitName = request.getUnitName();
        this.unitPrice = request.getUnitPrice();
        this.image = request.getImage();
        this.videoUrl = request.getVideoUrl();
        this.description = request.getDescription();
        this.status = request.getStatus();
    }
}
