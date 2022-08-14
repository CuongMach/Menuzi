package vn.anthinhphatjsc.menuzi.service.modules.admin.stores;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandMapper;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandResponse;
import vn.anthinhphatjsc.menuzi.service.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/modules/admin/stores")
public class StoreController {

    @Autowired
    StoreService storeService;

    @GetMapping
    public StoreResponse index(@Valid StorePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<StoreEntity> entityList = storeService.listStore(request.getFilters());
            return new StoreResponse(StoreMapper.toListDTO(entityList));
        }else {
            Page<StoreEntity> page = storeService.paginate(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new StoreResponse(StoreMapper.toPageDTO(page));
        }
    }

    @PostMapping
    public StoreResponse createUser(@Valid @RequestBody StoreRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StoreEntity entity = storeService.save(request.toEntity());
        return new StoreResponse(StoreMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public StoreResponse updateUser(@Valid @RequestBody StoreRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        StoreEntity entity = storeService.update(id, request.toEntity());
        return new StoreResponse(StoreMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public StoreResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        StoreEntity userEntity = storeService.delete(id);
        return new StoreResponse();
    }
}
