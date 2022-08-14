package vn.anthinhphatjsc.menuzi.service.modules.super_admin.roles;

import vn.anthinhphatjsc.menuzi.service.core.BaseRequest;
import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleRequest extends BaseRequest<RoleEntity> {
    private String name;

    public RoleEntity toEntity() {
        RoleEntity entity = new RoleEntity();
        return entity;
    }
}
