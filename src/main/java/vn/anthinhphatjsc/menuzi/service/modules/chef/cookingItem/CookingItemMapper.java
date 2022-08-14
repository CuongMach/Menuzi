package vn.anthinhphatjsc.menuzi.service.modules.chef.cookingItem;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.CookingItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CookingItemMapper {
    private static CookingItemMapper INSTANCE;

    public static CookingItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CookingItemMapper();
        }

        return INSTANCE;
    }

    public CookingItemMapper() {
    }

    public static CookingItemDTO toDTO(CookingItemEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, CookingItemDTO.class);
    }

    public static CookingItemEntity toEntity(CookingItemDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, CookingItemEntity.class);
    }

    public static Page<CookingItemDTO> toPageDTO(Page<CookingItemEntity> page) {
        return page.map(new Function<>() {
            @Override
            public CookingItemDTO apply(CookingItemEntity entity) {
                return CookingItemMapper.toDTO(entity);
            }
        });
    }
    public static List<CookingItemDTO> toListDTO(List<CookingItemEntity> entityList) {
        List<CookingItemDTO> list = new ArrayList<>();
        for (CookingItemEntity e : entityList) {
            list.add(CookingItemMapper.toDTO(e));
        }
        return list;
    }
}
