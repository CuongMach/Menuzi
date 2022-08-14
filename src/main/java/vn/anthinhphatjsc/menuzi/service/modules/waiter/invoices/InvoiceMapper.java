package vn.anthinhphatjsc.menuzi.service.modules.waiter.invoices;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.entities.InvoiceEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class InvoiceMapper {

    private static InvoiceMapper INSTANCE;

    public static InvoiceMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new InvoiceMapper();
        }

        return INSTANCE;
    }

    public InvoiceMapper() {
    }

    public static InvoiceDTO toDTO(InvoiceEntity role) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(role, InvoiceDTO.class);
    }

    public static InvoiceEntity toEntity(InvoiceDTO roleDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(roleDTO, InvoiceEntity.class);
    }

    public static Page<InvoiceDTO> toPageDTO(Page<InvoiceEntity> page) {
        return page.map(new Function<>() {
            @Override
            public InvoiceDTO apply(InvoiceEntity entity) {
                return InvoiceMapper.toDTO(entity);
            }
        });
    }

    public static List<InvoiceDTO> toListDTO(List<InvoiceEntity> list) {
        List<InvoiceDTO> invoiceDTOS = new ArrayList<>();
        for (InvoiceEntity e : list) {
            invoiceDTOS.add(InvoiceMapper.toDTO(e));
        }
        return invoiceDTOS;
    }
}