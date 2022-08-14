package vn.anthinhphatjsc.menuzi.service.modules.admin.users;

import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.admin.stores.StoreMapper;
import vn.anthinhphatjsc.menuzi.service.modules.admin.stores.StoreResponse;
import vn.anthinhphatjsc.menuzi.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("AdminUserController")
@RequestMapping("/api/modules/admin/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public UserResponse index(@Valid UserPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<UserEntity> entityList = userService.listAdmin(request.getFilters());
            return new UserResponse(UserMapper.toListDTO(entityList));
        }else {
            Page<UserEntity> page = userService.paginateAdmin(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new UserResponse(UserMapper.toPageDTO(page));
        }
    }

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody UserRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity userEntity = userService.saveadmin(request.toEntity());

        return new UserResponse(UserMapper.toDTO(userEntity));

    }

    @PutMapping(value = "{id}")
    public UserResponse updateUser(@Valid @RequestBody UserRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        UserEntity userEntity = userService.updateadmin(id, request.toEntity());

        return new UserResponse(UserMapper.toDTO(userEntity));

    }

    @DeleteMapping(value = "{id}")
    public UserResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        UserEntity userEntity = userService.deleteadmin(id);
        return new UserResponse();
    }

}
