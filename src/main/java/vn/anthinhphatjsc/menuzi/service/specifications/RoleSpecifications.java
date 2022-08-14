package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;

public class RoleSpecifications extends BaseSpecifications<RoleEntity> {

    private static RoleSpecifications INSTANCE;

    public static RoleSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleSpecifications();
        }

        return INSTANCE;
    }



}
