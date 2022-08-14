package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;

public class ProcessStatusSpecifications extends BaseSpecifications<ProcessStatusEntity> {
    private static ProcessStatusSpecifications INSTANCE;

    public static ProcessStatusSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProcessStatusSpecifications();
        }

        return INSTANCE;
    }
}
