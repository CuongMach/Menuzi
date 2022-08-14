package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;

public class InvoiceSpecifications extends BaseSpecifications<InvoiceEntity> {
    private static InvoiceSpecifications INSTANCE;

    public static InvoiceSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvoiceSpecifications();
        }

        return INSTANCE;
    }
}
