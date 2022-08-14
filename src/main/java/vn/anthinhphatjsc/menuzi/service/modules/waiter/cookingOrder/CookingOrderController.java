package vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.CookingOrderService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("CookingOrderController")
@RequestMapping("/api/modules/waiters/cookingOrders")
public class CookingOrderController {
    @Autowired
    CookingOrderService cookingOrderService;

    @GetMapping("/cookingItemID/{cookingItemID}")
    public CookingOrderResponse index(@PathVariable("cookingItemID") Long cookingItemID, @Valid CookingOrderPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null) {
            List<CookingOrderEntity> cookingOrderEntities = cookingOrderService.listCookingOrder(cookingItemID, request.getFilters());
            return new CookingOrderResponse(CookingOrderMapper.toListDTO(cookingOrderEntities));
        }
        Page<CookingOrderEntity> page = cookingOrderService.paginate(request.getPage() - 1, request.getLimit(), request.getFilters(), request.getOrders());
        return new CookingOrderResponse(CookingOrderMapper.toPageDTO(page));
    }

    @PostMapping("cookingItemID/{cookingItemID}")
    public CookingOrderResponse done(@PathVariable("cookingItemID") Long cookingItemID, @Valid @RequestBody CookingOrderRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        CookingOrderEntity entity = cookingOrderService.done(cookingItemID, request.toEntity());
        return new CookingOrderResponse(CookingOrderMapper.toDTO(entity));
    }
}
