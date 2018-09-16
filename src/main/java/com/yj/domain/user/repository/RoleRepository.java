package com.yj.domain.user.repository;

import com.yj.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>,JpaSpecificationExecutor<Role> {

    @Query(value = "select r.* from erp_user_role ur left join erp_role r on ur.roleId=r.id where  r.del_flag = 1 and ur.userid=:userId ", nativeQuery = true)
    List<Role> findRoleByUserId(@Param("userId") Long userId);





}
