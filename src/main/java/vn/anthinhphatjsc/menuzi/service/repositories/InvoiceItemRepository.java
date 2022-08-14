package vn.anthinhphatjsc.menuzi.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;
import vn.anthinhphatjsc.menuzi.service.specifications.InvoiceItemSpecifications;

import java.util.List;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItemEntity, Long>, JpaSpecificationExecutor<InvoiceItemEntity> {

    @Query(value = "select * from invoice_items where invoice_id =?1", nativeQuery = true)
    List<InvoiceItemEntity> findByInvoiceId(Long invoiceID);
}
