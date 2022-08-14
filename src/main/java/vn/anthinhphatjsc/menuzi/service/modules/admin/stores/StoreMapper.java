package vn.anthinhphatjsc.menuzi.service.modules.admin.stores;

import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandDTO;
import vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands.BrandMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class StoreMapper {

    private static StoreMapper INSTANCE;

    public static StoreMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new StoreMapper();
        }

        return INSTANCE;
    }

    public StoreMapper() {
    }

    public static StoreDTO toDTO(StoreEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, StoreDTO.class);
    }

    public static StoreEntity toEntity(StoreDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, StoreEntity.class);
    }

    public static Page<StoreDTO> toPageDTO(Page<StoreEntity> page) {
        return page.map(new Function<>() {
            @Override
            public StoreDTO apply(StoreEntity entity) {
                return StoreMapper.toDTO(entity);
            }
        });
    }
    public static List<StoreDTO> toListDTO(List<StoreEntity> entityList) {
        List<StoreDTO> list = new ArrayList<>();
        for (StoreEntity e : entityList) {
            list.add(StoreMapper.toDTO(e));
        }
        return list;
    }
}