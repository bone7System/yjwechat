package com.yj.domain.user.service;


import com.yj.domain.user.model.Role;
import com.yj.pojo.ReSult;
import com.yj.pojo.system.role.RoleDto;
import com.yj.pojo.system.role.RoleSearchDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleService {
    /**
     * 获取所有角色
     * @return
     */
    List<Role> findByAll();

    /**
     * 查找用户所有角色
     * @param userId
     * @return
     */
    List<Role>  findRoleByUserId(@Param("userId") Long userId);

    Role save(RoleDto role);

    Role update( RoleDto roleDto);

    ReSult seachRole(RoleSearchDto roleDto);

    ReSult deleteRole(Long roleId);
}
