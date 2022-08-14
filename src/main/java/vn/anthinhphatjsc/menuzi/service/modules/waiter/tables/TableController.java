package vn.anthinhphatjsc.menuzi.service.modules.waiter.tables;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.anthinhphatjsc.menuzi.service.entities.TableEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomValidationException;
import vn.anthinhphatjsc.menuzi.service.services.TableService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController("WaiterTableController")
@RequestMapping("/api/modules/waiters/tables")
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping
    public TableResponse index( @Valid TablePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null || request.getPage() == null){
            throw new CustomException(403,"lỗi không được bỏ trống limit và page");
        }
            Page<TableEntity> page = tableService.paginateWaiter( request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new TableResponse(TableMapper.toPageDTO(page));

    }
}
