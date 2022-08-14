package vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProcessStatusMapper {
    private static ProcessStatusMapper INSTANCE;

    public static ProcessStatusMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ProcessStatusMapper();
        }

        return INSTANCE;
    }

    public ProcessStatusMapper() {
    }

    public static ProcessStatusDTO toDTO(ProcessStatusEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, ProcessStatusDTO.class);
    }

    public static ProcessStatusEntity toEntity(ProcessStatusDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, ProcessStatusEntity.class);
    }

    public static Page<ProcessStatusDTO> toPageDTO(Page<ProcessStatusEntity> page) {
        return page.map(new Function<>() {
            @Override
            public ProcessStatusDTO apply(ProcessStatusEntity entity) {
                return ProcessStatusMapper.toDTO(entity);
            }
        });
    }
    public static List<ProcessStatusDTO> toListDTO(List<ProcessStatusEntity> entityList) {
        List<ProcessStatusDTO> list = new ArrayList<>();
        for (ProcessStatusEntity e : entityList) {
            list.add(ProcessStatusMapper.toDTO(e));
        }
        return list;
    }
}
