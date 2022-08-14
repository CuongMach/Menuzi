package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;

public class ItemCategorieSpecifications extends BaseSpecifications<ItemCategoryEntity> {

    private static ItemCategorieSpecifications INSTANCE;

    public static ItemCategorieSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemCategorieSpecifications();
        }

        return INSTANCE;
    }



}
