package vn.anthinhphatjsc.menuzi.service.modules.admin.stores;

import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StoreRequest {
    private Long brandId;
    @NotBlank(message = "không được bỏ trống name")
    private String name;
    private String address;
    private String phone;
    private String videoUrl;
    private String image;

    public StoreEntity toEntity() {
        StoreEntity entity = new StoreEntity();
        entity.setBrandId(this.brandId);
        entity.setName(this.name);
        entity.setAddress(this.address);
        entity.setPhone(this.phone);
        entity.setVideoUrl(this.videoUrl);
        entity.setImage(this.image);
        return entity;
    }

}
