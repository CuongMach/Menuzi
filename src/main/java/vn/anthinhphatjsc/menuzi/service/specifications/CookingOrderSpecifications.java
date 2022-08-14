package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;

public class CookingOrderSpecifications extends BaseSpecifications<CookingOrderEntity> {
    private static CookingOrderSpecifications INSTANCE;

    public static CookingOrderSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CookingOrderSpecifications();
        }

        return INSTANCE;
    }
}
