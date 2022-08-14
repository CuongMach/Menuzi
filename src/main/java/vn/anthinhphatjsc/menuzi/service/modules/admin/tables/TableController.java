package vn.anthinhphatjsc.menuzi.service.modules.admin.tables;

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
@RestController("AdminTableController")
@RequestMapping("/api/modules/admin/store/{storeID}/tables")
public class TableController {

    @Autowired
    TableService tableService;

    @GetMapping
    public TableResponse index(@PathVariable("storeID") Long storeID, @Valid TablePaginationRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        if (request.getLimit() == null && request.getPage() == null){
            List<TableEntity> itemEntities = tableService.listAdmin(storeID,request.getFilters());
            return new TableResponse(TableMapper.toListDTO(itemEntities));
        }else{
            Page<TableEntity> page = tableService.paginateAdmin(storeID, request.getPage()-1, request.getLimit(), request.getFilters(), request.getOrders());
            return new TableResponse(TableMapper.toPageDTO(page));
        }

    }

    @PostMapping
    public TableResponse createUser(@PathVariable("storeID") Long storeID, @Valid @RequestBody TableRequest request, BindingResult bindingResult) throws CustomException, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        TableEntity entity = tableService.saveAdmin(request.toEntity(), storeID);
        return new TableResponse(TableMapper.toDTO(entity));

    }

    @PutMapping(value = "{id}")
    public TableResponse updateUser(@PathVariable("id") Long id, @PathVariable("storeID") Long storeID, @Valid @RequestBody TableRequest request, BindingResult bindingResult) throws Exception, CustomValidationException {
        if (bindingResult.hasErrors()) {
            throw new CustomValidationException(bindingResult.getAllErrors());
        }
        TableEntity entity = tableService.updateAdmin(id, request.toEntity(), storeID);
        return new TableResponse(TableMapper.toDTO(entity));

    }

    @DeleteMapping(value = "{id}")
    public TableResponse deleteUser(@PathVariable("id") Long id, @PathVariable("storeID") Long storeID) throws Exception, CustomValidationException {
        TableEntity userEntity = tableService.deleteAdmin(id,storeID);
        return new TableResponse();
    }
}
