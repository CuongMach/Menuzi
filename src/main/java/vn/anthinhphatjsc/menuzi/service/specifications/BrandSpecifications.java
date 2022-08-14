package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;

public class BrandSpecifications extends BaseSpecifications<BrandEntity> {

    private static BrandSpecifications INSTANCE;

    public static BrandSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BrandSpecifications();
        }

        return INSTANCE;
    }



}
