package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;

public class InvoiceItemSpecifications extends BaseSpecifications<InvoiceItemEntity> {
    private static InvoiceItemSpecifications INSTANCE;

    public static InvoiceItemSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvoiceItemSpecifications();
        }

        return INSTANCE;
    }
}
