package vn.anthinhphatjsc.menuzi.service.modules.manager.itemCategories;

import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.ItemCategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("ManagerItemCategorieController")
@RequestMapping("/api/modules/manager/item_categories")
public class ItemCategorieController {

    @Autowired
    ItemCategorieService itemCategorieService;

    @GetMapping
    public ItemCategorieResponse index(@Valid ItemCategoriePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<ItemCategoryEntity> itemCategoryEntities = itemCategorieService.listItemCategorieManager(request.getFilters());
            return new ItemCategorieResponse(ItemCategorieMapper.toListDTO(itemCategoryEntities));
        }
        Page<ItemCategoryEntity> page = itemCategorieService.paginateManager(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
        return new ItemCategorieResponse(ItemCategorieMapper.toPageDTO(page));
    }

    @PostMapping
    public ItemCategorieResponse createUser(@Valid @RequestBody ItemCategorieRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemCategoryEntity entity = itemCategorieService.saveManager(request.toEntity());
        return new ItemCategorieResponse(ItemCategorieMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public ItemCategorieResponse updateUser(@Valid @RequestBody ItemCategorieRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemCategoryEntity entity = itemCategorieService.updateManager(id, request.toEntity());
        return new ItemCategorieResponse(ItemCategorieMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public ItemCategorieResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        ItemCategoryEntity userEntity = itemCategorieService.deleteManager(id);
        return new ItemCategorieResponse();
    }
}
