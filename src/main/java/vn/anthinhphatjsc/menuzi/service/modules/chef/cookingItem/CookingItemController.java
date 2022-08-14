package vn.anthinhphatjsc.menuzi.service.modules.chef.cookingItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.CookingItemService;
import vn.anthinhphatjsc.menuzi.service.services.CookingOrderService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("CookingItemController")
@RequestMapping("/api/modules/chefs/cookingItems")
public class CookingItemController {
    @Autowired
    CookingItemService cookingItemService;

    @GetMapping
    public CookingItemResponse index(@Valid CookingItemPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null) {
            List<CookingItemEntity> cookingItemEntities = cookingItemService.listCookingItem(request.getFilters());
            return new CookingItemResponse(CookingItemMapper.toListDTO(cookingItemEntities));
        }
        Page<CookingItemEntity> page = cookingItemService.paginate(request.getPage() - 1, request.getLimit(), request.getFilters(), request.getOrders());
        return new CookingItemResponse(CookingItemMapper.toPageDTO(page));
    }

    @PostMapping("/processStatusID/{processStatusID}/brandID/{brandID}/storeID/{storeID}/chefID/{chefID}")
    public CookingItemResponse doing(@PathVariable("processStatusID") Long processStatusID, @PathVariable("brandID") Long brandID, @PathVariable("storeID") Long storeID, @PathVariable("chefID") Long chefID, @Valid @RequestBody CookingItemRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CookingItemEntity entity = cookingItemService.doing(processStatusID, brandID, storeID, chefID, request.toEntity());
        return new CookingItemResponse(CookingItemMapper.toDTO(entity));
    }

    @PutMapping("/add/cookingItemID/{cookingItemID}")
    public CookingItemResponse add(@PathVariable("cookingItemID") Long cookingItemID) throws CustomException, CustomValidationException {
        return new CookingItemResponse(CookingItemMapper.toDTO(cookingItemService.addQuantity(cookingItemID)));
    }

    @PutMapping("/sub/cookingItemID/{cookingItemID}")
    public CookingItemResponse sub(@PathVariable("cookingItemID") Long cookingItemID) throws CustomException, CustomValidationException {
        return new CookingItemResponse(CookingItemMapper.toDTO(cookingItemService.subtractQuantity(cookingItemID)));
    }
}
