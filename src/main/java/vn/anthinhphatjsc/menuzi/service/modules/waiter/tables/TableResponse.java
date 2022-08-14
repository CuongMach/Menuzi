package vn.anthinhphatjsc.menuzi.service.modules.waiter.tables;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class TableResponse extends BaseResponse<TableDTO> {
    public TableResponse(Page<TableDTO> toPageDTO) {

        super(toPageDTO);
    }
    public TableResponse(List<TableDTO> toListDTO) {

        super(toListDTO);
    }

    public TableResponse(TableDTO toDTO) {
        super(toDTO);
    }

    public TableResponse() {
        super();
    }


}
