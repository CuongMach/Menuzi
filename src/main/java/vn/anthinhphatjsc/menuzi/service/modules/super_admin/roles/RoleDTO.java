package vn.anthinhphatjsc.menuzi.service.modules.super_admin.roles;

import vn.anthinhphatjsc.menuzi.service.core.BaseDTO;
import lombok.*;

import java.io.Serializable;


@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleDTO extends BaseDTO implements Serializable {
    private Long id;
    private String name;
}
