package vn.anthinhphatjsc.menuzi.service.modules.waiter.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.admin.items.ItemMapper;
import vn.anthinhphatjsc.menuzi.service.modules.admin.items.ItemPaginationRequest;
import vn.anthinhphatjsc.menuzi.service.modules.admin.items.ItemRequest;
import vn.anthinhphatjsc.menuzi.service.modules.admin.items.ItemResponse;
import vn.anthinhphatjsc.menuzi.service.services.OrderService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("OrderController")
@RequestMapping("/api/modules/waiters/brand/{brandID}/store/{storeID}/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/table/{tableID}")
    public OrderResponse index(@PathVariable("tableID") Long tableID, @Valid OrderPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<OrderEntity> orderEntities = orderService.listOrder(tableID,request.getFilters());
            return new OrderResponse(OrderMapper.toListDTO(orderEntities));
        }else{
            Page<OrderEntity> page = orderService.paginate(tableID, request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new OrderResponse(OrderMapper.toPageDTO(page));
        }
    }

    @PostMapping
    public OrderResponse createOrder(@PathVariable("storeID") Long storeID,@PathVariable("brandID") Long brandID, @Valid @RequestBody OrderRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity entity = orderService.save(request.toEntity(),storeID,brandID, request.toListItems());
        return new OrderResponse(OrderMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public OrderResponse updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderRequest request, BindingResult bindingResult) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderEntity entity = orderService.update(id, request.toEntity());
        return new OrderResponse(OrderMapper.toDTO(entity));
    }

    @DeleteMapping(value = "{id}")
    public OrderResponse deleteUser(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        OrderEntity entity = orderService.delete(id);
        return new OrderResponse(OrderMapper.toDTO(entity));
    }
}
