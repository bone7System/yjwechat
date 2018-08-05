package com.yj.domain.user.repository;

import com.yj.domain.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long>,JpaSpecificationExecutor<Role> {

    @Query(value = "select r.* from erp_user_role ur left join erp_role r on ur.roleId=r.id where  r.status = 1 and ur.userid=:userId ", nativeQuery = true)
    List<Role> findRoleByUserId(@Param("userId") Long userId);



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
