package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.*;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.InvoiceItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.InvoiceRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.OrderItemRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.OrderRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.InvoiceItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.InvoiceSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("invoiceItemService")
@Transactional(rollbackFor = Throwable.class)
public class InvoiceItemServiceImpl implements InvoiceItemService{
    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public Page<InvoiceItemEntity> paginate(Long invoiceID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<InvoiceItemEntity> specifications = InvoiceItemSpecifications.getInstance().getQueryResult(filters);

        return invoiceItemRepository.findAll(specifications, pageable);
    }

    @Override
    public List<InvoiceItemEntity> listInvoiceItem(Long invoiceID,List<Filter> filters) {
//        Specification<InvoiceItemEntity> specifications = InvoiceItemSpecifications.getInstance().getQueryResult(filters);
        List<InvoiceItemEntity> invoiceItemEntities = invoiceItemRepository.findByInvoiceId(invoiceID);
        return invoiceItemEntities;
    }
}
