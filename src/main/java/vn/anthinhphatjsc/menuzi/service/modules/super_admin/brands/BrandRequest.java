package vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BrandRequest {
    @NotBlank(message = "không được để trống name")
    private String name;
    private String image;

    public BrandEntity toEntity() {
        BrandEntity entity = new BrandEntity();
        entity.setName(this.name);
        entity.setImage(this.image);
        return entity;
    }

}
