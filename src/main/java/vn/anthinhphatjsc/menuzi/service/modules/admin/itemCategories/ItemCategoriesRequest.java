package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import lombok.*;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemCategoriesRequest {
    private Long storeId;
    private Long brandId;
    @NotBlank(message = "không được bỏ trống name")
    private String name;
    private String image;

    public ItemCategoryEntity toEntity() {
        ItemCategoryEntity entity = new ItemCategoryEntity();
        entity.setBrandId(this.brandId);
        entity.setStoreId(this.storeId);
        entity.setName(this.name);
        entity.setImage(this.image);
        return entity;
    }

}
