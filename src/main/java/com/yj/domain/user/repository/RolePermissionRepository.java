package com.yj.domain.user.repository;

import com.yj.domain.user.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {
    /**
     *  查找角色所有权限
     * @param roleId
     * @return
     */
    List<RolePermission> findByRoleIdAndClient(Long roleId,Long client);
}
