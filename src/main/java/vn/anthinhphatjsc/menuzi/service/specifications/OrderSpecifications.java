package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;

public class OrderSpecifications extends BaseSpecifications<OrderEntity> {
    private static OrderSpecifications INSTANCE;

    public static OrderSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderSpecifications();
        }

        return INSTANCE;
    }
}
