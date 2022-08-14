package vn.anthinhphatjsc.menuzi.service.modules.admin.itemCategories;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemCategoriesMapper {

    private static ItemCategoriesMapper INSTANCE;

    public static ItemCategoriesMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemCategoriesMapper();
        }

        return INSTANCE;
    }

    public ItemCategoriesMapper() {
    }

    public static ItemCategoriesDTO toDTO(ItemCategoryEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, ItemCategoriesDTO.class);
    }

    public static ItemCategoryEntity toEntity(ItemCategoriesDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, ItemCategoryEntity.class);
    }

    public static Page<ItemCategoriesDTO> toPageDTO(Page<ItemCategoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ItemCategoriesDTO apply(ItemCategoryEntity entity) {
                return ItemCategoriesMapper.toDTO(entity);
            }
        });
    }
    public static List<ItemCategoriesDTO> toListDTO(List<ItemCategoryEntity> entityList) {
        List<ItemCategoriesDTO> list = new ArrayList<>();
        for (ItemCategoryEntity e : entityList) {
            list.add(ItemCategoriesMapper.toDTO(e));
        }
        return list;
    }
}