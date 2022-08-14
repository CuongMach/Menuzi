package vn.anthinhphatjsc.menuzi.service.modules.admin.items;

import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("AdminItemController")
@RequestMapping("/api/modules/admin/store/{storeID}/items")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping
    public ItemResponse index(@PathVariable("storeID") Long storeID, @Valid ItemPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<ItemEntity> itemEntities = itemService.listItemAdmin(storeID,request.getFilters());
            return new ItemResponse(ItemMapper.toListDTO(itemEntities));
        }else{
            Page<ItemEntity> page = itemService.paginateAdmin(storeID, request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new ItemResponse(ItemMapper.toPageDTO(page));
        }

    }

    @PostMapping
    public ItemResponse createUser(@PathVariable("storeID") Long storeID, @Valid @RequestBody ItemRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemEntity entity = itemService.saveAdmin(request.toEntity(), storeID);
        return new ItemResponse(ItemMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public ItemResponse updateUser(@PathVariable("id") Long id, @PathVariable("storeID") Long storeID, @Valid @RequestBody ItemRequest request, BindingResult bindingResult) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ItemEntity entity = itemService.updateAdmin(id, request.toEntity(), storeID);
        return new ItemResponse(ItemMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public ItemResponse deleteUser(@PathVariable("id") Long id, @PathVariable("storeID") Long storeID) throws Exception, CustomValidationException {
        ItemEntity userEntity = itemService.deleteAdmin(id,storeID);
        return new ItemResponse();
    }
}
