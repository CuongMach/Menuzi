package vn.anthinhphatjsc.menuzi.service.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.anthinhphatjsc.menuzi.service.auth.AuthUser;
import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.core.QueryOperator;
import vn.anthinhphatjsc.menuzi.service.entities.*;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.BrandRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.RoleRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.StoreRepository;
import vn.anthinhphatjsc.menuzi.service.repositories.UserRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.ItemSpecifications;
import vn.anthinhphatjsc.menuzi.service.specifications.UserSpecifications;
import vn.anthinhphatjsc.menuzi.service.utils.AuthUtil;

import java.util.*;

@Service("userService")
@Transactional(rollbackFor = Throwable.class)
public class UserServiceImpl implements UserService {
    private final String input = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890qwertyuiopasdfghjklzxcvbnm";

    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    BrandRepository brandRepository;

    @Override
    public UserEntity login(String username, String password) throws CustomException {
        if (username == null || "".equals(username.trim())) {
            throw new CustomException(403, "không được bỏ trống user");
        } else if (password == null || "".equals(password.trim())) {
            throw new CustomException(403, "không được bỏ trống password");
        }
        Optional<UserEntity> userEntity = userRepository.login(username);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            BCryptPasswordEncoder b = new BCryptPasswordEncoder();
            if (b.matches(password, userEntity.get().getPassword())) {
                return user;
            }
        }

        return null;
    }

    @Override
    public Optional<AuthUser> findByToken(String token) {
        Optional<UserEntity> userEntityOptional = userRepository.findByRememberToken(token);
        if (userEntityOptional.isPresent()) {
            UserEntity userEntity = userEntityOptional.get();

            Optional<RoleEntity> roleEntityOptional = roleRepository.findById(userEntity.getRoleId());
            if (roleEntityOptional.isEmpty()) {
                return Optional.empty();
            }

            RoleEntity roleEntity = roleEntityOptional.get();

            AuthUser user = new AuthUser(userEntity, roleEntity);

            return Optional.of(user);
        }
        return Optional.empty();
    }

    @Override
    public Page<UserEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        filters.add(new Filter("brand_id", QueryOperator.EQUALS, brandId.toString(), null));

        Long storeId = AuthUtil.getInstance().getCurrentUser().getStoreId();
        if (storeId > 0) {
            filters.add(new Filter("store_id", QueryOperator.EQUALS, storeId.toString(), null));
        }

        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);

        return userRepository.findAll(specifications, pageable);
    }

    @Override
    public UserEntity saveSuperAdmin(UserEntity requestEntity) throws CustomException {
        List<UserEntity> entity = userRepository.findAll();
        for (UserEntity user : entity) {
            if (requestEntity.getUsername().equals(user.getUsername())) {
                throw new CustomException(403, "lỗi username không được trùng");
            }
        }
        Optional<BrandEntity> brandEntity = brandRepository.findById(requestEntity.getBrandId());
        if (brandEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy brand");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setData(requestEntity);
        userEntity.setRoleId(2L);
        userEntity.setStoreId(0L);
        userEntity.setRememberToken(RandomStringUtils.random(100, input));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity saveadmin(UserEntity requestEntity) throws CustomException {
        List<UserEntity> entity = userRepository.findAll();
        for (UserEntity user : entity) {
            if (requestEntity.getUsername().equals(user.getUsername())) {
                throw new CustomException(403, "lỗi username không được trùng");
            }
        }
        Optional<RoleEntity> roleEntity = roleRepository.findById(requestEntity.getRoleId());
        if (roleEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy role");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(requestEntity.getStoreId());
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }
        if (requestEntity.getRoleId() <= 2){
            throw new CustomException(401,"bạn không có quyền");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setData(requestEntity);
        userEntity.setRoleId(requestEntity.getRoleId());
        userEntity.setStoreId(requestEntity.getStoreId());
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        userEntity.setRememberToken(RandomStringUtils.random(100, input));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateSuperAdmin(Long id, UserEntity requestEntity) throws CustomException {
        List<UserEntity> entity = userRepository.findAll();
        for (UserEntity user : entity) {
            if (requestEntity.getUsername().equals(user.getUsername())) {
                throw new CustomException(403, "lỗi username không được trùng");
            }
        }
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của user");
        }
        Optional<BrandEntity> brandEntity = brandRepository.findById(requestEntity.getBrandId());
        if (brandEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy brand");
        }
        UserEntity userEntity = optional.get();
        userEntity.setData(requestEntity);
        userEntity.setRoleId(2L);
        userEntity.setStoreId(0L);
        userEntity.setRememberToken(RandomStringUtils.random(100, input));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity updateadmin(Long id, UserEntity requestEntity) throws CustomException {
        List<UserEntity> entity = userRepository.findAll();
        Long idBrand = AuthUtil.getInstance().getCurrentUser().getBrandId();
        for (UserEntity user : entity) {
            if (requestEntity.getUsername().equals(user.getUsername())) {
                throw new CustomException(403, "lỗi username không được trùng");
            }
        }
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của user");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(401, "bạn không thể sửa tài khoản của thương hiệu khác");
        }else if (optional.get().getRoleId() <= 2){
            throw new CustomException(401, "bạn không có quyền sửa tài khoản admin và super admin");
        }
        Optional<RoleEntity> roleEntity = roleRepository.findById(requestEntity.getRoleId());
        if (roleEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy role");
        }
        Optional<StoreEntity> storeEntity = storeRepository.findById(requestEntity.getStoreId());
        if (storeEntity.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy store");
        }
        if (requestEntity.getRoleId() <= 2){
            throw new CustomException(401,"bạn không có quyền");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setData(requestEntity);
        userEntity.setRoleId(requestEntity.getRoleId());
        userEntity.setStoreId(requestEntity.getStoreId());
        userEntity.setBrandId(AuthUtil.getInstance().getCurrentUser().getBrandId());
        userEntity.setRememberToken(RandomStringUtils.random(100, input));
        return userRepository.save(userEntity);
    }

    @Override
    public UserEntity deleteSuperAdmin(Long id) throws CustomException {
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của user ");
        }
        UserEntity entity = userRepository.getById(id);
        userRepository.delete(entity);
        return entity;

    }

    @Override
    public UserEntity deleteadmin(Long id) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        Optional<UserEntity> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của user");
        }else if (!Objects.equals(optional.get().getBrandId(), brandId)){
            throw new CustomException(401, "bạn không thể xóa tài khoản của thương hiệu khác");
        }else if (optional.get().getRoleId() <= 2){
            throw new CustomException(401, "bạn không có quyền xóa tài khoản admin và super admin");
        }
        UserEntity entity = userRepository.getById(id);
        userRepository.delete(entity);
        return entity;
    }


    @Override
    public Page<UserEntity> paginateSuperAdmin(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        filters.add(new Filter("roleId", QueryOperator.EQUALS, "2", null));

        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);

        return userRepository.findAll(specifications, pageable);
    }

    @Override
    public List<UserEntity> listSuperAdmin(List<Filter> filters) throws CustomException {
        filters.add(new Filter("roleId", QueryOperator.EQUALS, "2", null));
        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);
        return userRepository.findAll(specifications);
    }

    @Override
    public Page<UserEntity> paginateAdmin(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));
        filters.add(new Filter("roleId", QueryOperator.GREATER_THAN, "2", null));


        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);

        return userRepository.findAll(specifications, pageable);
    }

    @Override
    public List<UserEntity> listAdmin(List<Filter> filters) throws CustomException {
        Long brandId = AuthUtil.getInstance().getCurrentUser().getBrandId();
        filters.add(new Filter("brandId", QueryOperator.EQUALS, brandId.toString(), null));
        filters.add(new Filter("roleId", QueryOperator.GREATER_THAN, "2", null));
        Specification<UserEntity> specifications = UserSpecifications.getInstance().getQueryResult(filters);
        return userRepository.findAll(specifications);
    }

}