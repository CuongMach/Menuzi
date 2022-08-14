package vn.anthinhphatjsc.menuzi.service.auth;

import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

public class AuthResponse extends BaseResponse<AuthDTO> {
    public AuthResponse(int status, String message, AuthDTO data) {
        super(status, message, data);
    }

    public AuthResponse() {
    }

    public AuthResponse(AuthDTO data) {
        super(data);
    }
}
