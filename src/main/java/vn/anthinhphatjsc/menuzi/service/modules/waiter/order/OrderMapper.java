package vn.anthinhphatjsc.menuzi.service.modules.waiter.order;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderMapper {
    private static OrderMapper INSTANCE;

    public static OrderMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderMapper();
        }

        return INSTANCE;
    }

    public OrderMapper() {
    }

    public static OrderDTO toDTO(OrderEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, OrderDTO.class);
    }

    public static OrderEntity toEntity(OrderDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, OrderEntity.class);
    }

    public static Page<OrderDTO> toPageDTO(Page<OrderEntity> page) {
        return page.map(new Function<>() {
            @Override
            public OrderDTO apply(OrderEntity entity) {
                return OrderMapper.toDTO(entity);
            }
        });
    }
    public static List<OrderDTO> toListDTO(List<OrderEntity> entityList) {
        List<OrderDTO> list = new ArrayList<>();
        for (OrderEntity e : entityList) {
            list.add(OrderMapper.toDTO(e));
        }
        return list;
    }
}
