package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface RoleService {

    Page<RoleEntity> paginate(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    RoleEntity save(RoleEntity requestEntity) throws CustomException;

    RoleEntity delete(Long id) throws CustomException;

    RoleEntity update(Long id, RoleEntity request) throws CustomException;
}