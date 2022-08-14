package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.auth.AuthUser;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.ItemEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    UserEntity login(String username, String password) throws CustomException;

    Optional<AuthUser> findByToken(String token);

    Page<UserEntity> paginate(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    UserEntity saveSuperAdmin(UserEntity requestEntity) throws CustomException;

    UserEntity saveadmin(UserEntity requestEntity) throws CustomException;

    UserEntity updateSuperAdmin(Long id, UserEntity requestEntity) throws CustomException;

    UserEntity updateadmin(Long id, UserEntity requestEntity) throws CustomException;

    UserEntity deleteSuperAdmin(Long id) throws CustomException;

    UserEntity deleteadmin(Long id) throws CustomException;

    Page<UserEntity> paginateSuperAdmin(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<UserEntity> listSuperAdmin(List<Filter> whereParams) throws CustomException;

    Page<UserEntity> paginateAdmin(Integer page, Integer limit, List<Filter> whereParams, Map<String, String> sortBy) throws CustomException;

    List<UserEntity> listAdmin(List<Filter> whereParams) throws CustomException;

}