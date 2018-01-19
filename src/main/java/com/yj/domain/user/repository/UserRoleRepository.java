package com.yj.domain.user.repository;

import com.yj.domain.user.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole save(UserRole userRole);

    @Modifying
    @Query(
            value = "delete from user_role  where role_id = :roleId and user_id = :userId",
            nativeQuery = true
    )
    Integer deleteByUserIdAndRoleId(@Param("userId") Long userId, @Param("roleId") Long roleId);

    Integer deleteAllByUserId(Long userId);

}
