package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;

public class StoreSpecifications extends BaseSpecifications<StoreEntity> {

    private static StoreSpecifications INSTANCE;

    public static StoreSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StoreSpecifications();
        }

        return INSTANCE;
    }



}
