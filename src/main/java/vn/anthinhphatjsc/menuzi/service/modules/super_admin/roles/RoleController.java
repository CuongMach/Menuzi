package vn.anthinhphatjsc.menuzi.service.modules.super_admin.roles;

import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController("SuperAdminRoleController")
@RequestMapping("/api/modules/super_admin/roles")
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping
    public RoleResponse index(@Valid RolePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        Page<RoleEntity> page = roleService.paginate(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
        return new RoleResponse(RoleMapper.toPageDTO(page));
    }

    @PostMapping
    public RoleResponse createUser(@Valid @RequestBody RoleRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        RoleEntity entity = roleService.save(request.toEntity());

        return new RoleResponse(RoleMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public RoleResponse updateUser(@Valid @RequestBody RoleRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        RoleEntity entity = roleService.update(id, request.toEntity());

        return new RoleResponse(RoleMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public RoleResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        RoleEntity userEntity = roleService.delete(id);
        return new RoleResponse();
    }
}
