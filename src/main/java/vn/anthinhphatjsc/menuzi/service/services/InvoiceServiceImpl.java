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
import vn.anthinhphatjsc.menuzi.service.repositories.*;
import vn.anthinhphatjsc.menuzi.service.specifications.InvoiceSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("invoiceService")
@Transactional(rollbackFor = Throwable.class)
public class InvoiceServiceImpl implements InvoiceService{

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    BrandRepository brandRepository;

    @Autowired
    StoreRepository storeRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    InvoiceItemRepository invoiceItemRepository;

    @Override
    public Page<InvoiceEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();

        Pageable pageable = PageRequest.of(page, limit, sort);


        Specification<InvoiceEntity> specifications = InvoiceSpecifications.getInstance().getQueryResult(filters);

        return invoiceRepository.findAll(specifications, pageable);
    }

    @Override
    public InvoiceEntity save(InvoiceEntity invoiceEntity) throws CustomException {
        return null;
    }

    @Override
    public InvoiceEntity delete(Long id) throws CustomException {
        Optional<InvoiceEntity> optional = invoiceRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của invoice ");
        }
        InvoiceEntity entity = invoiceRepository.getById(id);
        invoiceRepository.delete(entity);
        return entity;
    }

    @Override
    public InvoiceEntity update(Long id, InvoiceEntity invoiceEntity) throws CustomException {
        Optional<InvoiceEntity> optional = invoiceRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của invoice");
        }
        InvoiceEntity entity = optional.get();
        entity.setData(invoiceEntity);
        return invoiceRepository.save(entity);
    }

    @Override
    public List<InvoiceEntity> listInvoice(List<Filter> filters) {
        Specification<InvoiceEntity> specifications = InvoiceSpecifications.getInstance().getQueryResult(filters);
        List<InvoiceEntity> invoiceEntityList = invoiceRepository.findAll(specifications);
        return invoiceEntityList;
    }

    @Override
    @Transactional
    public InvoiceEntity payTheBill(Long orderID, InvoiceEntity invoiceEntity) throws CustomException {
        Optional<OrderEntity> optional = orderRepository.findById(orderID);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy order");
        }
        optional.get().setStatus(2);
        orderRepository.save(optional.get());
        InvoiceEntity entity = new InvoiceEntity();
        entity.setOrderId(orderID);
        entity.setStoreId(optional.get().getStoreId());
        entity.setBrandId(optional.get().getBrandId());
        entity.setCustomer_name(optional.get().getCustomer_name());
        entity.setCustomer_phone(optional.get().getCustomer_phone());
        entity.setTotalAll(optional.get().getTotal());
        entity.setData(invoiceEntity);
        invoiceRepository.save(entity);
        List<OrderItemEntity> orderItemEntities = orderItemRepository.findByOrderId(orderID);
        for (int i = 0; i < orderItemEntities.size(); i++) {
            InvoiceItemEntity invoiceItemEntity = new InvoiceItemEntity();
            invoiceItemEntity.setInvoiceId(entity.getId());
            invoiceItemEntity.setUnitName(orderItemEntities.get(i).getUnitName());
            invoiceItemEntity.setUnitPrice(orderItemEntities.get(i).getUnitPrice());
            invoiceItemEntity.setQuantity(orderItemEntities.get(i).getQuantity());
            invoiceItemEntity.setTotal(orderItemEntities.get(i).getTotal());
            invoiceItemRepository.save(invoiceItemEntity);
        }
        return entity;
    }
}
