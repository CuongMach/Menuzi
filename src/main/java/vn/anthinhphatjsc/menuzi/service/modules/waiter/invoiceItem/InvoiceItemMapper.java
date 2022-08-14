package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoiceItem;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceItemEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InvoiceItemMapper {

    private static InvoiceItemMapper INSTANCE;

    public static InvoiceItemMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvoiceItemMapper();
        }

        return INSTANCE;
    }

    public InvoiceItemMapper() {
    }

    public static InvoiceItemDTO toDTO(InvoiceItemEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, InvoiceItemDTO.class);
    }

    public static InvoiceItemEntity toEntity(InvoiceItemDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, InvoiceItemEntity.class);
    }

    public static Page<InvoiceItemDTO> toPageDTO(Page<InvoiceItemEntity> page) {
        return page.map(new Function<>() {
            @Override
            public InvoiceItemDTO apply(InvoiceItemEntity entity) {
                return InvoiceItemMapper.toDTO(entity);
            }
        });
    }

    public static List<InvoiceItemDTO> toListDTO(List<InvoiceItemEntity> list) {
        List<InvoiceItemDTO> itemDTOS = new ArrayList<>();
        for (InvoiceItemEntity e : list) {
            itemDTOS.add(InvoiceItemMapper.toDTO(e));
        }
        return itemDTOS;
    }
}