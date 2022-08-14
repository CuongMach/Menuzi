package vn.anthinhphatjsc.menuzi.service.specifications;


import vn.anthinhphatjsc.menuzi.service.core.BaseSpecifications;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;

public class UserSpecifications extends BaseSpecifications<UserEntity> {

    private static UserSpecifications INSTANCE;

    public static UserSpecifications getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserSpecifications();
        }

        return INSTANCE;
    }

}
