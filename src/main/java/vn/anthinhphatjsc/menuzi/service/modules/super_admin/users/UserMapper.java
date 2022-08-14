package vn.anthinhphatjsc.menuzi.service.modules.super_admin.users;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandDTO;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class UserMapper {

    private static UserMapper INSTANCE;

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }

        return INSTANCE;
    }

    public UserMapper() {
    }

    public static UserDTO toDTO(UserEntity user) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    public static UserEntity toEntity(UserDTO userDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(userDTO, UserEntity.class);
    }

    public static Page<UserDTO> toPageDTO(Page<UserEntity> page) {
        return page.map(new Function<>() {
            @Override
            public UserDTO apply(UserEntity entity) {
                return UserMapper.toDTO(entity);
            }
        });
    }

    public static List<UserDTO> toListDTO(List<UserEntity> entityList) {
        List<UserDTO> list = new ArrayList<>();
        for (UserEntity e : entityList) {
            list.add(UserMapper.toDTO(e));
        }
        return list;
    }
}