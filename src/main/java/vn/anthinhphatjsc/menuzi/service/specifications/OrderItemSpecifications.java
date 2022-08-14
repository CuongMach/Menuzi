package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;

public class OrderItemSpecifications extends BaseSpecifications<OrderItemEntity> {
    private static OrderItemSpecifications INSTANCE;

    public static OrderItemSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderItemSpecifications();
        }

        return INSTANCE;
    }
}
