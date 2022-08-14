package vn.anthinhphatjsc.menuzi.service.modules.admin.items;

import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemRequest {
    private Long categoriesId;
    private Long storeId;
    private Long brandId;
    @NotBlank(message = "không được bỏ trống unitName")
    private String unitName;
    @NotBlank(message = "không được bỏ trống unitPrice")
    private Double unitPrice;
    private String image;
    private String videoUrl;
    private String description;
    private Integer status;

    public ItemEntity toEntity() {
        ItemEntity entity = new ItemEntity();
        entity.setCategoriesId(this.categoriesId);
        entity.setStoreId(this.storeId);
        entity.setBrandId(this.brandId);
        entity.setUnitName(this.unitName);
        entity.setUnitPrice(this.unitPrice);
        entity.setImage(this.image);
        entity.setVideoUrl(this.videoUrl);
        entity.setDescription(this.description);
        entity.setStatus(this.status);
        return entity;
    }

}
