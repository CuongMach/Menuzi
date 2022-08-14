package vn.anthinhphatjsc.menuzi.service.modules.waiter.cookingOrder;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class CookingOrderMapper {
    private static CookingOrderMapper INSTANCE;

    public static CookingOrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CookingOrderMapper();
        }

        return INSTANCE;
    }

    public CookingOrderMapper() {
    }

    public static CookingOrderDTO toDTO(CookingOrderEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, CookingOrderDTO.class);
    }

    public static CookingOrderEntity toEntity(CookingOrderDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, CookingOrderEntity.class);
    }

    public static Page<CookingOrderDTO> toPageDTO(Page<CookingOrderEntity> page) {
        return page.map(new Function<>() {
            @Override
            public CookingOrderDTO apply(CookingOrderEntity entity) {
                return CookingOrderMapper.toDTO(entity);
            }
        });
    }
    public static List<CookingOrderDTO> toListDTO(List<CookingOrderEntity> entityList) {
        List<CookingOrderDTO> list = new ArrayList<>();
        for (CookingOrderEntity e : entityList) {
            list.add(CookingOrderMapper.toDTO(e));
        }
        return list;
    }
}
