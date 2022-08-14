package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoiceItem;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class InvoiceItemResponse extends BaseResponse<InvoiceItemDTO> {
    public InvoiceItemResponse(Page<InvoiceItemDTO> toPageDTO) {

        super(toPageDTO);
    }
    public InvoiceItemResponse(List<InvoiceItemDTO> toListDTO) {

        super(toListDTO);
    }

    public InvoiceItemResponse(InvoiceItemDTO toDTO) {
        super(toDTO);
    }

    public InvoiceItemResponse() {
        super();
    }


}
