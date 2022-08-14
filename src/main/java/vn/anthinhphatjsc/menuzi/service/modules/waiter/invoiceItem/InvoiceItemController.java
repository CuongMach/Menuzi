package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoiceItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices.InvoiceMapper;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices.InvoicePaginationRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices.InvoiceRequest;
import vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices.InvoiceResponse;
import vn.anthinhphatjsc.menuzi.service.services.InvoiceItemService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("InvoiceItemController")
@RequestMapping("/api/modules/waiters/invoiceItems")
public class InvoiceItemController {
    @Autowired
    InvoiceItemService invoiceItemService;

    @GetMapping("/invoiceID/{invoiceID}")
    public InvoiceItemResponse index(@PathVariable("invoiceID") Long invoiceID, @Valid InvoiceItemPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null) {
            List<InvoiceItemEntity> invoiceItemEntities = invoiceItemService.listInvoiceItem(invoiceID, request.getFilters());
            return new InvoiceItemResponse(InvoiceItemMapper.toListDTO(invoiceItemEntities));
        }
        Page<InvoiceItemEntity> page = invoiceItemService.paginate(invoiceID, request.getPage() - 1, request.getLimit(), request.getFilters(), request.getOrders());
        return new InvoiceItemResponse(InvoiceItemMapper.toPageDTO(page));
    }
}

