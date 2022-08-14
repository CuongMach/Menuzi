package vn.anthinhphatjsc.menuzi.service.modules.super_admin.users;

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
public class UserDTO extends BaseDTO implements Serializable {
    private Long id;
    private Long roleId;
    private Long storeId;
    private Long brandId;
    private String name;
    private String username;
    private String phone;
}
