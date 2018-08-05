package com.yj.domain.user.service;

import com.google.common.collect.Lists;
import com.yj.domain.user.model.Role;
import com.yj.domain.user.repository.RoleRepository;
import com.yj.pojo.ReSult;
import com.yj.pojo.RoleDto;
import com.yj.pojo.RoleSearchDto;
import com.yj.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


/**
 *
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Override
    public List<Role> findByAll() {
        return roleRepository.findAll();
    }

    @Override
    public List<Role> findRoleByUserId(Long userId) {
        return roleRepository.findRoleByUserId(userId);
    }

    @Override
    public Role save(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        role.setCreateTime(new Date());//设置创建日期
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role update(RoleDto roleDto) {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto,role);
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public ReSult seachRole(RoleSearchDto roleDto) {
       List list= roleRepository.findAll(new Specification<Role>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = Lists.newArrayList();
                if(roleDto.getId()!=null){
                    predicates.add(cb.equal(root.get("id"),roleDto.getId()));
                }
                if(!StringUtils.isEmpty(roleDto.getDescription())){
                    predicates.add(cb.like(root.get("description"),roleDto.getDescription()));
                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();

            }
        });
        return ReSult.success(list);
    }

    @Override
    public ReSult deleteRole(Long roleId) {
        Role role= roleRepository.getOne(roleId);
        if(role!=null){
            roleRepository.delete(role);
            return ReSult.success(null);
        }
        return ReSult.error("删除失败",null);
    }


}
