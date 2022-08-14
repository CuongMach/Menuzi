package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;

public class TableSpecifications extends BaseSpecifications<TableEntity> {

    private static TableSpecifications INSTANCE;

    public static TableSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableSpecifications();
        }

        return INSTANCE;
    }



}
