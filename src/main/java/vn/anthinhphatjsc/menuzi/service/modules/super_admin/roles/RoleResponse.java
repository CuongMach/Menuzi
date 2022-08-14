package vn.anthinhphatjsc.menuzi.service.modules.super_admin.roles;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;

public class RoleResponse extends BaseResponse<RoleDTO> {
    public RoleResponse(Page<RoleDTO> toPageDTO) {

        super(toPageDTO);
    }

    public RoleResponse(RoleDTO toDTO) {
        super(toDTO);
    }

    public RoleResponse() {
        super();
    }


}
