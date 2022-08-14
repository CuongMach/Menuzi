package vn.anthinhphatjsc.menuzi.service.auth;

import vn.anthinhphatjsc.menuzi.service.services.UserService;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public AuthResponse login(@RequestBody AuthRequest request) throws Exception {
        UserEntity userEntity = userService.login(request.getUsername(), request.getPassword());
        if (userEntity != null) {
            return new AuthResponse(AuthMapper.getInstance().toDTO(userEntity));
        } else {
            throw new Exception("Tài khoản hoặc mật khẩu sai");
        }
    }
}
