package vn.anthinhphatjsc.menuzi.service.modules.manager.itemCategories;

import vn.anthinhphatjsc.menuzi.service.entities.ItemCategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.StoreEntity;
import vn.anthinhphatjsc.menuzi.service.modules.admin.stores.StoreDTO;
import vn.anthinhphatjsc.menuzi.service.modules.admin.stores.StoreMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ItemCategorieMapper {

    private static ItemCategorieMapper INSTANCE;

    public static ItemCategorieMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemCategorieMapper();
        }

        return INSTANCE;
    }

    public ItemCategorieMapper() {
    }

    public static ItemCategorieDTO toDTO(ItemCategoryEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, ItemCategorieDTO.class);
    }

    public static ItemCategoryEntity toEntity(ItemCategorieDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, ItemCategoryEntity.class);
    }

    public static Page<ItemCategorieDTO> toPageDTO(Page<ItemCategoryEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ItemCategorieDTO apply(ItemCategoryEntity entity) {
                return ItemCategorieMapper.toDTO(entity);
            }
        });
    }
    public static List<ItemCategorieDTO> toListDTO(List<ItemCategoryEntity> entityList) {
        List<ItemCategorieDTO> list = new ArrayList<>();
        for (ItemCategoryEntity e : entityList) {
            list.add(ItemCategorieMapper.toDTO(e));
        }
        return list;
    }
}