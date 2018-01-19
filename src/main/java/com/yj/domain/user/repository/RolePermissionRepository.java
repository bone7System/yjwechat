package com.yj.domain.user.repository;

import com.yj.domain.user.model.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RolePermissionRepository extends JpaRepository<RolePermission, Long> {

    @Modifying
    @Query(
            value = "delete from role_permission  where role_id = :roleId and permission_id = :permissionId",
            nativeQuery = true
    )
    Integer deleteByRoleIdAndPermissionId(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);

    Integer deleteAllByRoleId(Long roleId);
}
