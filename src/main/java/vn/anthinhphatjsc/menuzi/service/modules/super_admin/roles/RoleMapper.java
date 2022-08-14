package vn.anthinhphatjsc.menuzi.service.modules.super_admin.roles;

import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.function.Function;

public class RoleMapper {

    private static RoleMapper INSTANCE;

    public static RoleMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleMapper();
        }

        return INSTANCE;
    }

    public RoleMapper() {
    }

    public static RoleDTO toDTO(RoleEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, RoleDTO.class);
    }

    public static RoleEntity toEntity(RoleDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, RoleEntity.class);
    }

    public static Page<RoleDTO> toPageDTO(Page<RoleEntity> page) {
        return page.map(new Function<>() {
            @Override
            public RoleDTO apply(RoleEntity entity) {
                return RoleMapper.toDTO(entity);
            }
        });
    }
}