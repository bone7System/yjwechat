package com.yj.domain.user.repository;

import com.yj.domain.user.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Query(value = "select p.* from user_role ur " +
        "        left join role r on ur.role_id=r.id " +
        "        right join role_permission rp on rp.role_id=r.id  " +
        "        left join permission p on p.id=rp.permission_id " +
        "       where  ur.user_id=:userId and r.status = 1", nativeQuery = true)
    List<Permission> findPermissionByUserId(@Param("userId") Long userId);
//
//    List<Permission> findRolePermissions(@Param("roleId") Long roleId);
}
