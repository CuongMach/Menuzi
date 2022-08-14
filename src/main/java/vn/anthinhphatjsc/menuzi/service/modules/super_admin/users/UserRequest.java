package vn.anthinhphatjsc.menuzi.service.modules.super_admin.users;

import vn.anthinhphatjsc.menuzi.service.core.BaseRequest;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest extends BaseRequest<UserEntity> {

    private Long roleId;
    private Long storeId;
    @NotNull(message = "thương hiệu không được để trống")
    private Long brandId;
    @NotBlank(message = "username không được để trống")
    private String username;
    @NotBlank(message = "password không được để trống")
    private String password;
    @NotBlank(message = "name không được để trống")
    private String name;
    private String phone;

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setRoleId(this.roleId);
        entity.setStoreId(this.storeId);
        entity.setBrandId(this.brandId);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setName(this.name);
        entity.setPhone(this.phone);
        return entity;
    }
}
