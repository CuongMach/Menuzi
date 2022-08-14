package vn.anthinhphatjsc.menuzi.service.modules.admin.users;

import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private Long roleId;
    private Long storeId;
    private Long brandId;
    @NotBlank(message = "username không được để trống")
    private String username;
    @NotBlank(message = "password không được để trống")
    private String password;
    @NotBlank(message = "name không được để trống")
    private String name;
    private String email;
    private String phone;

    public UserEntity toEntity() {
        UserEntity entity = new UserEntity();
        entity.setRoleId(this.roleId);
        entity.setStoreId(this.storeId);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setName(this.name);
        this.setEmail(this.email);
        entity.setPhone(this.phone);
        return entity;
    }


}
