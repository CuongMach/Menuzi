package vn.anthinhphatjsc.menuzi.service.specifications;

import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;

public class CookingItemSpecifications extends BaseSpecifications<CookingItemEntity> {
    private static CookingItemSpecifications INSTANCE;

    public static CookingItemSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CookingItemSpecifications();
        }

        return INSTANCE;
    }
}
