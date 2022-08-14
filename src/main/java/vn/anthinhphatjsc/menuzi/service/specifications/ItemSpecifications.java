package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

public class ItemSpecifications extends BaseSpecifications<ItemEntity> {

    private static ItemSpecifications INSTANCE;

    public static ItemSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemSpecifications();
        }

        return INSTANCE;
    }



}
