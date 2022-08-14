package vn.anthinhphatjsc.menuzi.service.services;

import vn.anthinhphatjsc.menuzi.service.core.Filter;
import vn.anthinhphatjsc.menuzi.service.entities.RoleEntity;
import vn.anthinhphatjsc.menuzi.service.exceptions.CustomException;
import vn.anthinhphatjsc.menuzi.service.repositories.RoleRepository;
import vn.anthinhphatjsc.menuzi.service.specifications.RoleSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service("adminRoleService")
@Transactional(rollbackFor = Throwable.class)
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Page<RoleEntity> paginate(Integer page, Integer limit, List<Filter> filters, Map<String, String> sortBy) throws CustomException {
        List<Sort.Order> orders = new ArrayList<>();
        for (String field : sortBy.keySet()) {
            orders.add(new Sort.Order(Sort.Direction.fromString(sortBy.get(field)), field));
        }
        Sort sort = orders.size() > 0 ? Sort.by(orders) : Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page, limit, sort);

        Specification<RoleEntity> specifications = RoleSpecifications.getInstance().getQueryResult(filters);

        return roleRepository.findAll(specifications, pageable);
    }

    @Override
    public RoleEntity save(RoleEntity requestEntity) throws CustomException {
        RoleEntity userEntity = new RoleEntity();
        userEntity.setData(requestEntity);
        return roleRepository.save(userEntity);
    }

    @Override
    public RoleEntity delete(Long id) throws CustomException {
        Optional<RoleEntity> optional = roleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của role ");
        }
        RoleEntity entity = roleRepository.getById(id);
        roleRepository.delete(entity);
        return entity;

    }


    @Override
    public RoleEntity update(Long id, RoleEntity requestEntity) throws CustomException {
        Optional<RoleEntity> optional = roleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CustomException(403, "Lỗi không tìm thấy id của role");
        }
        RoleEntity userEntity = optional.get();
        userEntity.setData(requestEntity);
        return roleRepository.save(userEntity);
    }

}