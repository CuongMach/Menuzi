package vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.BrandService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/modules/super_admin/brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public BrandResponse index(@Valid BrandPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<BrandEntity> listBrandEntities = brandService.listBrand(request.getFilters());
            return new BrandResponse(BrandMapper.toListDTO(listBrandEntities));
        }else {
            Page<BrandEntity> page = brandService.paginate(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new BrandResponse(BrandMapper.toPageDTO(page));
        }
    }

    @PostMapping
    public BrandResponse createUser(@Valid @RequestBody BrandRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BrandEntity entity = brandService.save(request.toEntity());
        return new BrandResponse(BrandMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public BrandResponse updateUser(@Valid @RequestBody BrandRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        BrandEntity entity = brandService.update(id, request.toEntity());
        return new BrandResponse(BrandMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public BrandResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        BrandEntity userEntity = brandService.delete(id);
        return new BrandResponse();
    }
}
