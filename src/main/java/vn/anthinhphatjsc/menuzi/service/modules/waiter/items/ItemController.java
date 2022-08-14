package vn.anthinhphatjsc.menuzi.service.modules.waiter.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.ItemService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("WaiterItemController")
@RequestMapping("/api/modules/waiters/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ItemResponse index(@Valid ItemPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<ItemEntity> itemEntities = itemService.listItemManager(request.getFilters());
            return new ItemResponse(ItemMapper.toListDTO(itemEntities));
        }else{
            Page<ItemEntity> page = itemService.paginateManager(request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new ItemResponse(ItemMapper.toPageDTO(page));
        }

    }


}
