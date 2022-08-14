package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class InvoiceResponse extends BaseResponse<InvoiceDTO> {
    public InvoiceResponse(Page<InvoiceDTO> toPageDTO) {

        super(toPageDTO);
    }
    public InvoiceResponse(List<InvoiceDTO> toListDTO) {

        super(toListDTO);
    }

    public InvoiceResponse(InvoiceDTO toDTO) {
        super(toDTO);
    }

    public InvoiceResponse() {
        super();
    }


}
