package com.yj.domain.user.repository;

import com.yj.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "select r.* from user_role ur left join role r on ur.role_id=r.id where ur.user_id=:userId and r.status = 1 ", nativeQuery = true)
    List<Role> findRoleByUserId(@Param("userId") Long userId);

//    Page<User> findUserByRoleName(String role, Pageable pageable);


    @Query(value =
            "SELECT r.*                                                         "+
            "FROM role r LEFT JOIN (SELECT                                      "+
            "                         o.appid,                                  "+
            "                         r.org_id,                                 "+
            "                         r.role_id                                 "+
            "                       FROM organization o                         "+
            "                       LEFT JOIN org_role r ON o.id = r.org_id) o  "+
            "            ON r.id = o.role_id                                    "+
            "WHERE (o.appid = :appid  and r.status = 1) or r.issystem = 1      ",
            nativeQuery = true)
    List<Role> findRoleListByAppid(@Param("appid") String appid);

}
