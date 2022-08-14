package vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus;

import org.springframework.data.domain.Page;
import vn.anthinhphatjsc.menuzi.service.core.BaseResponse;

import java.util.List;

public class ProcessStatusResponse extends BaseResponse<ProcessStatusDTO> {
    public ProcessStatusResponse(Page<ProcessStatusDTO> toPageDTO) {

        super(toPageDTO);
    }
    public ProcessStatusResponse(List<ProcessStatusDTO> toListDTO) {

        super(toListDTO);
    }

    public ProcessStatusResponse(ProcessStatusDTO toDTO) {
        super(toDTO);
    }

    public ProcessStatusResponse() {
        super();
    }
}
