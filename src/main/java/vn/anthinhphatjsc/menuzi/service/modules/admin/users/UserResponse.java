package vn.anthinhphatjsc.menuzi.service.modules.admin.users;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserResponse extends BaseResponse<UserDTO> {
    public UserResponse(Page<UserDTO> toPageDTO) {

        super(toPageDTO);
    }

    public UserResponse(List<UserDTO> toListDTO) {

        super(toListDTO);
    }

    public UserResponse(UserDTO toDTO) {
        super(toDTO);
    }

    public UserResponse() {
        super();
    }


}
