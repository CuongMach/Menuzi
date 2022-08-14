package vn.anthinhphatjsc.menuzi.service.modules.super_admin.users;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandMapper;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandResponse;
import vn.anthinhphatjsc.menuzi.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("SuperAdminUserController")
@RequestMapping("/api/modules/super_admin/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public UserResponse index(@Valid UserPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<UserEntity> listBrandEntities = userService.listSuperAdmin(request.getFilters());
            return new UserResponse(UserMapper.toListDTO(listBrandEntities));
        }else {
            Page<UserEntity> page = userService.paginateSuperAdmin(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new UserResponse(UserMapper.toPageDTO(page));
        }

    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity userEntity = userService.saveSuperAdmin(request.toEntity());

        return new UserResponse(UserMapper.toDTO(userEntity));

    }

    @PutMapping(value = "{id}")
    public UserResponse updateUser(@Valid @RequestBody UserRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity userEntity = userService.updateSuperAdmin(id, request.toEntity());

        return new UserResponse(UserMapper.toDTO(userEntity));

    }

    @DeleteMapping(value = "{id}")
    public UserResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        UserEntity userEntity = userService.deleteSuperAdmin(id);
        return new UserResponse();
    }

}
