package com.yj.domain.user.repository;

import com.yj.domain.user.model.RoleInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleInfoRepository extends JpaRepository<RoleInfo, Long> {

    @Query(value =
            "SELECT                                                   "+
            "  r.*,                                                   "+
            "  o.org_id,                                             "+
            "  o.orgname                                              "+
            "FROM user_role ur LEFT JOIN role r ON ur.role_id = r.id  "+
            "  LEFT JOIN (SELECT                                      "+
            "               o.orgname,                                "+
            "               oe.org_id,                                "+
            "               oe.role_id                                "+
            "             FROM organization o                         "+
            "               LEFT JOIN org_role oe                     "+
            "                 ON o.id = oe.org_id) o                  "+
            "    ON r.id = o.role_id                                  "+
            "WHERE ur.user_id = :userId AND r.status = 1             ",
            nativeQuery = true)
    List<RoleInfo> findRoleByUserId(@Param("userId") Long userId);
    @Query(
            value=
            "SELECT                               "+
            "  r.*,                               "+
            "  o.org_id,                          "+
            "  o.orgname                          "+
            "FROM role r                          "+
            "LEFT JOIN (SELECT                   "+
            "               o.orgname,            "+
            "               oe.org_id,            "+
            "               oe.role_id            "+
            "             FROM organization o     "+
            "            LEFT JOIN org_role oe    "+
            "             ON o.id = oe.org_id) o  "+
            "ON r.id = o.role_id                  "+
            "WHERE r.id = :id                   ",
            nativeQuery = true)
    RoleInfo findById(@Param("id")Long id);
}
