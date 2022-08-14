package vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.OrderItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class OrderItemMapper {
    private static OrderItemMapper INSTANCE;

    public static OrderItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new OrderItemMapper();
        }

        return INSTANCE;
    }

    public OrderItemMapper() {
    }

    public static OrderItemDTO toDTO(OrderItemEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, OrderItemDTO.class);
    }

    public static OrderItemEntity toEntity(OrderItemDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, OrderItemEntity.class);
    }

    public static Page<OrderItemDTO> toPageDTO(Page<OrderItemEntity> page) {
        return page.map(new Function<>() {
            @Override
            public OrderItemDTO apply(OrderItemEntity entity) {
                return OrderItemMapper.toDTO(entity);
            }
        });
    }
    public static List<OrderItemDTO> toListDTO(List<OrderItemEntity> entityList) {
        List<OrderItemDTO> list = new ArrayList<>();
        for (OrderItemEntity e : entityList) {
            list.add(OrderItemMapper.toDTO(e));
        }
        return list;
    }
}
