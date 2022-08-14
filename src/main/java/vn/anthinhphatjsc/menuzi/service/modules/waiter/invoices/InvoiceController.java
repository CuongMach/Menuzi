package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder.CookingOrderMapper;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder.CookingOrderPaginationRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder.CookingOrderRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder.CookingOrderResponse;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.order.OrderMapper;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.order.OrderRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.order.OrderResponse;
import vn.anthinhphatjsc.menuzi.service.services.InvoiceService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("InvoiceController")
@RequestMapping("/api/modules/waiters/invoices")
public class InvoiceController {
    @Autowired
    InvoiceService invoiceService;

    @GetMapping
    public InvoiceResponse index(@Valid InvoicePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null) {
            List<InvoiceEntity> invoiceEntities = invoiceService.listInvoice(request.getFilters());
            return new InvoiceResponse(InvoiceMapper.toListDTO(invoiceEntities));
        }
        Page<InvoiceEntity> page = invoiceService.paginate(request.getPage() - 1, request.getLimit(), request.getFilters(), request.getOrders());
        return new InvoiceResponse(InvoiceMapper.toPageDTO(page));
    }

    @PostMapping("/orderID/{orderID}")
    public InvoiceResponse createInvoice(@PathVariable("orderID") Long orderID, @Valid @RequestBody InvoiceRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        InvoiceEntity entity = invoiceService.payTheBill(orderID, request.toEntity());
        return new InvoiceResponse(InvoiceMapper.toDTO(entity));
    }

    @DeleteMapping(value = "{id}")
    public InvoiceResponse deleteInvoice(@PathVariable("id") Long id) throws Exception, CustomValidationException {
        InvoiceEntity entity = invoiceService.delete(id);
        return new InvoiceResponse(InvoiceMapper.toDTO(entity));
    }

    @PutMapping(value = "{id}")
    public InvoiceResponse updateInvoice(@PathVariable("id") Long id, @Valid @RequestBody InvoiceRequest request, BindingResult bindingResult) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        InvoiceEntity entity = invoiceService.update(id, request.toEntity());
        return new InvoiceResponse(InvoiceMapper.toDTO(entity));
    }
}
