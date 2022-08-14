package vn.anthinhphatjsc.menuzi.service.modules.waiter.items;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemMapper {

    private static ItemMapper INSTANCE;

    public static ItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemMapper();
        }

        return INSTANCE;
    }

    public ItemMapper() {
    }

    public static ItemDTO toDTO(ItemEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, ItemDTO.class);
    }

    public static ItemEntity toEntity(ItemDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, ItemEntity.class);
    }

    public static Page<ItemDTO> toPageDTO(Page<ItemEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ItemDTO apply(ItemEntity entity) {
                return ItemMapper.toDTO(entity);
            }
        });
    }

    public static List<ItemDTO> toListDTO(List<ItemEntity> list) {
        List<ItemDTO> itemDTOS = new ArrayList<>();
        for (ItemEntity e : list) {
            itemDTOS.add(ItemMapper.toDTO(e));
        }
        return itemDTOS;
    }
}