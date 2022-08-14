package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;

import java.util.List;
import java.util.Map;

public interface InvoiceService {
    Page<InvoiceEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

    InvoiceEntity save(InvoiceEntity invoiceEntity) throws CustomException;

    InvoiceEntity delete(Long id) throws CustomException;


    InvoiceEntity update(Long id, InvoiceEntity invoiceEntity) throws CustomException;

    List<InvoiceEntity> listInvoice(List<Filter> filters);

    InvoiceEntity payTheBill(Long id, InvoiceEntity invoiceEntity) throws  CustomException;

}
