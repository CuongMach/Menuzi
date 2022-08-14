package vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus.ProcessStatusMapper;
import vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus.ProcessStatusRequest;
import vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus.ProcessStatusResponse;
import vn.anthinhphatjsc.menuzi.service.services.OrderItemService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("OrderItemController")
@RequestMapping("/api/modules/chefs/orderItems")
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;

    @GetMapping("/orders/{orderID}")
    public OrderItemResponse index(@PathVariable("orderID") Long orderID, @Valid OrderItemPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<OrderItemEntity> orderEntities = orderItemService.listOrderItem(orderID,request.getFilters());
            return new OrderItemResponse(OrderItemMapper.toListDTO(orderEntities));
        }else{
            Page<OrderItemEntity> page = orderItemService.paginate(orderID, request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new OrderItemResponse(OrderItemMapper.toPageDTO(page));
        }
    }

    @PutMapping(value = "{id}")
    public OrderItemResponse updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderItemRequest request, BindingResult bindingResult) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        OrderItemEntity entity = orderItemService.update(id, request.toEntity());
        return new OrderItemResponse(OrderItemMapper.toDTO(entity));
    }

    @DeleteMapping(value = "{id}")
    public OrderItemResponse deleteOrder(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        OrderItemEntity entity = orderItemService.delete(id);
        return new OrderItemResponse(OrderItemMapper.toDTO(entity));
    }

//    @PostMapping("/{id}/accept")
//    public OrderItemResponse acceptOrderItem(@PathVariable("id") Long id, @Valid @RequestBody OrderItemRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
//        if (bindingResult.hasErrors()) {
//            throw new CustomValidationException(bindingResult.getAllErrors());
//        }
//        OrderItemEntity entity = orderItemService.accept(id, request.toEntity());
//        return new OrderItemResponse(OrderItemMapper.toDTO(entity));
//
//    }
}
