package vn.anthinhphatjsc.menuzi.service.modules.super_admin.brands;

import org.modelmapper.TypeToken;
import vn.anthinhphatjsc.menuzi.service.entities.BrandEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BrandMapper {

    private static BrandMapper INSTANCE;

    public static BrandMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BrandMapper();
        }

        return INSTANCE;
    }

    public BrandMapper() {
    }

    public static BrandDTO toDTO(BrandEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, BrandDTO.class);
    }

    public static BrandEntity toEntity(BrandDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, BrandEntity.class);
    }

    public static Page<BrandDTO> toPageDTO(Page<BrandEntity> page) {
        return page.map(new Function<>() {
            @Override
            public BrandDTO apply(BrandEntity entity) {
                return BrandMapper.toDTO(entity);
            }
        });
    }

    public static List<BrandDTO> toListDTO(List<BrandEntity> entityList) {
        List<BrandDTO> list = new ArrayList<>();
        for (BrandEntity e : entityList) {
            list.add(BrandMapper.toDTO(e));
        }
        return list;
    }


}