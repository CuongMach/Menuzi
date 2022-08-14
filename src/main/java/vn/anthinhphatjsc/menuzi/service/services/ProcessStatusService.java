package vn.anthinhphatjsc.menuzi.service.services;

import org.springframework.data.domain.Page;
import org.springframework.security.access.method.P;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.OrderEntity;
import vn.anthinhphatjsc.menuzi.service.entities.ProcessStatusEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.modules.chef.orderItem.OrderItemRequest;
import vn.anthinhphatjsc.menuzi.service.modules.chef.processStatus.ProcessStatusRequest;

import java.util.List;
import java.util.Map;

public interface ProcessStatusService {
    Page<ProcessStatusEntity> paginate(Long orderItemID, Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException;

    List<ProcessStatusEntity> listProcessStatus(Long orderID, List<Filter> whereParams) throws CustomException;

    ProcessStatusEntity save( Long orderID,ProcessStatusEntity processStatusEntity) throws CustomException;

    ProcessStatusEntity delete(Long id) throws CustomException;


    ProcessStatusEntity update(Long id, ProcessStatusEntity processStatusEntity) throws CustomException;

    ProcessStatusEntity accept(Long orderItemID, ProcessStatusEntity entity) throws CustomException;

    ProcessStatusEntity cancel(Long orderItemID, ProcessStatusEntity entity) throws CustomException;

}
