package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.ItemCategorieService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("AdminItemCategoriesController")
@RequestMapping("/api/modules/admin/store/{storeID}/item_categories")
public class ItemCategoriesController {

    @Autowired
    ItemCategorieService itemCategorieService;

    @GetMapping
    public ItemCategoriesResponse index(@PathVariable("storeID") Long storeID, @Valid ItemCategoriesPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<ItemCategoryEntity> itemCategoryEntities = itemCategorieService.listItemCategorieAdmin(storeID,request.getFilters());
            return new ItemCategoriesResponse(ItemCategoriesMapper.toListDTO(itemCategoryEntities));
        }
        Page<ItemCategoryEntity> page = itemCategorieService.paginateAdmin(storeID,request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
        return new ItemCategoriesResponse(ItemCategoriesMapper.toPageDTO(page));
    }

    @PostMapping
    public ItemCategoriesResponse createUser(@PathVariable("storeID") Long storeID, @Valid @RequestBody ItemCategoriesRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemCategoryEntity entity = itemCategorieService.saveAdmin(storeID,request.toEntity());
        return new ItemCategoriesResponse(ItemCategoriesMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public ItemCategoriesResponse updateUser(@PathVariable("storeID") Long storeID, @Valid @RequestBody ItemCategoriesRequest request, BindingResult bindingResult, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemCategoryEntity entity = itemCategorieService.updateAdmin(storeID,id, request.toEntity());
        return new ItemCategoriesResponse(ItemCategoriesMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public ItemCategoriesResponse deleteUser(@PathVariable("storeID") Long storeID, @PathVariable("id") Long id) throws Exception, CustomValidationException {
        ItemCategoryEntity userEntity = itemCategorieService.deleteAdmin(storeID,id);
        return new ItemCategoriesResponse();
    }
}
