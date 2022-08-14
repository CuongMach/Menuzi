package vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.CookingOrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.CookingOrderService;
import vn.anthinhphatjsc.menuzi.service.services.ProcessStatusService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("ProcessStatusController")
@RequestMapping("/api/modules/chefs/processStatus")
public class ProcessStatusController {
    @Autowired
    ProcessStatusService processStatusService;

    @GetMapping("orderID/{orderID}")
    public ProcessStatusResponse index(@PathVariable("orderID") Long orderID, @Valid ProcessStatusPaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null) {
            List<ProcessStatusEntity> processStatusEntities = processStatusService.listProcessStatus(orderID,request.getFilters());
            return new ProcessStatusResponse(ProcessStatusMapper.toListDTO(processStatusEntities));
        }
        Page<ProcessStatusEntity> page = processStatusService.paginate(orderID, request.getPage() - 1, request.getLimit(), request.getFilters(), request.getOrders());
        return new ProcessStatusResponse(ProcessStatusMapper.toPageDTO(page));
    }

    @PostMapping("/accept/orderItemID/{orderItemID}")
    public ProcessStatusResponse acceptOrderItem(@PathVariable("orderItemID") Long orderItemID, @Valid @RequestBody ProcessStatusRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProcessStatusEntity entity = processStatusService.accept(orderItemID,request.toEntity());
        return new ProcessStatusResponse(ProcessStatusMapper.toDTO(entity));

    }

    @PostMapping("/cancel/orderItemID/{orderItemID}")
    public ProcessStatusResponse cancelOrderItem(@PathVariable("orderItemID") Long orderItemID, @Valid @RequestBody ProcessStatusRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        ProcessStatusEntity entity = processStatusService.cancel(orderItemID,request.toEntity());
        return new ProcessStatusResponse(ProcessStatusMapper.toDTO(entity));

    }

}
