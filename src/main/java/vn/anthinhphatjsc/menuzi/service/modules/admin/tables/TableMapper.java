package vn.anthinhphatjsc.menuzi.service.modules.admin.tables;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TableMapper {

    private static TableMapper INSTANCE;

    public static TableMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TableMapper();
        }

        return INSTANCE;
    }

    public TableMapper() {
    }

    public static TableDTO toDTO(TableEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, TableDTO.class);
    }

    public static TableEntity toEntity(TableDTO tableDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(tableDTO, TableEntity.class);
    }

    public static Page<TableDTO> toPageDTO(Page<TableEntity> page) {
        return page.map(new Function<>() {
            @Override
            public TableDTO apply(TableEntity entity) {
                return TableMapper.toDTO(entity);
            }
        });
    }

    public static List<TableDTO> toListDTO(List<TableEntity> list) {
        List<TableDTO> itemDTOS = new ArrayList<>();
        for (TableEntity e : list) {
            itemDTOS.add(TableMapper.toDTO(e));
        }
        return itemDTOS;
    }
}