package com.yj.domain.user.service;

import com.yj.domain.user.model.Role;
import com.yj.domain.user.repository.RoleRepository;
import com.yj.pojo.RoleDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
