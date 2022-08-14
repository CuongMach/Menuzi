package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import javax.imageio.IIOException;
import java.util.List;
import java.util.Map;

public interface InvoiceItemService {
    Page<InvoiceItemEntity> paginate(Long invoiceID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

    List<InvoiceItemEntity> listInvoiceItem(Long invoiceID,List<Filter> filters);
}
