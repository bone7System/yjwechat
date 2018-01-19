package com.yj.domain.user.repository;

import com.yj.domain.user.model.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {
    @Query(
            value=
                "SELECT u.id,u.username, u.email, u.enabled," +
                 " u.wechat, u.create_time, u.update_time," +
                 " p.mobile, p.sex, p.name, p.avatar,      "+
                "  u.status, p.policeno, p.position,  "+
                "  p.identity, p.remark, p.sort, so.orgid, so.orgname  "+
                "FROM user u                               "+
                "LEFT JOIN profile p ON u.id = p.user_id   "+
                "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                "where u.id = :userId                       ",
            nativeQuery = true
    )
    UserDetail findUserDetail(@Param("userId") Long userId);

    @Query(
            value=
                    "SELECT DISTINCT u.id,u.username, u.email, u.enabled," +
                    " u.wechat, u.create_time, u.update_time," +
                    " p.mobile, p.sex, p.name, p.avatar,      "+
                    "  u.status, p.policeno, p.position,       "+
                    "  p.identity, p.remark, p.sort,so.orgid, so.orgname "+
                    "FROM user u                               "+
                    "LEFT JOIN profile p ON u.id = p.user_id   "+
                    "LEFT JOIN user_org_view v ON  u.id = v.user_id  "+
                    "LEFT JOIN org_user ou on ou.user_id = u.id "+
                    "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                    "WHERE v.appid = :appid and u.username like :mobile   "+
                    "and p.name like :name and p.policeno like :policeno "+
                    "and ou.org_id = :orgid   and u.status != 999        "+
                    " ORDER BY u.id  \n-- #pageable\n       ",
            countQuery =
                    "SELECT count(DISTINCT u.id)              "+
                    "FROM user u                               "+
                    "LEFT JOIN profile p ON u.id = p.user_id   "+
                    "LEFT JOIN user_org_view v ON  u.id = v.user_id  "+
                    "LEFT JOIN org_user ou on ou.user_id = u.id "+
                    "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                    "WHERE v.appid = :appid  and u.username like :mobile     "+
                    "and p.name like :name and p.policeno like :policeno "+
                    "and ou.org_id = :orgid and u.status != 999",
            nativeQuery = true
    )
    Page<UserDetail> findUserPage(@Param("appid")String appid, @Param("mobile")String mobile, @Param("name")String name, @Param("policeno")String policeno, @Param("orgid")Long orgid, Pageable pageable);

    @Query(
            value=
                    "SELECT DISTINCT u.id,u.username, u.email, u.enabled," +
                            " u.wechat, u.create_time, u.update_time," +
                            " p.mobile, p.sex, p.name, p.avatar,      "+
                            "  u.status, p.policeno, p.position,       "+
                            "  p.identity , p.remark, p.sort, so.orgid, so.orgname "+
                            "FROM user u                               "+
                            "LEFT JOIN profile p ON u.id = p.user_id   "+
                            "LEFT JOIN user_org_view v ON  u.id = v.user_id  "+
                            "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                            "WHERE v.appid = :appid and u.username like :mobile   "+
                            "and p.name like :name and p.policeno like :policeno "+
                            "and u.status != 999 "+
                            " ORDER BY u.id  \n-- #pageable\n       ",
            countQuery =
                    "SELECT count(DISTINCT u.id)              "+
                            "FROM user u                               "+
                            "LEFT JOIN profile p ON u.id = p.user_id   "+
                            "LEFT JOIN user_org_view v ON  u.id = v.user_id  "+
                            "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                            "WHERE v.appid = :appid  and u.username like :mobile     "+
                            "and p.name like :name and p.policeno like :policeno "+
                            "and u.status != 999 ",
            nativeQuery = true
    )
    Page<UserDetail> findUserPage(@Param("appid")String appid, @Param("mobile")String mobile, @Param("name")String name, @Param("policeno")String policeno, Pageable pageable);


    @Query(
            value=
                    "SELECT u.id,u.username,u.password, u.email, u.enabled," +
                            " u.wechat, u.create_time, u.update_time," +
                            " p.mobile, p.sex, p.name, p.avatar,      "+
                            "  u.status, p.policeno, p.position,       "+
                            "  p.identity, p.remark, p.sort            "+
                            "FROM user u                               "+
                            "LEFT JOIN profile p ON u.id = p.user_id   "+
                            "where u.id = :userId                       ",
            nativeQuery = true
    )
    UserDetail findUserDetailIncPassword(@Param("userId") Long userId);

    @Query(
            value=
                    "SELECT u.id,u.username, u.email, u.enabled," +
                            " u.wechat, u.create_time, u.update_time," +
                            " p.mobile, p.sex, p.name, p.avatar,      "+
                            "  u.status, p.policeno, p.position,       "+
                            "  p.identity, p.remark, p.sort, so.orgid, so.orgname            "+
                            "FROM user u                               "+
                            "LEFT JOIN profile p ON u.id = p.user_id   "+
                            "LEFT JOIN sel_user_organization so ON so.id = u.id "+
                            "where u.username = :username and u.status != 999   ",
            nativeQuery = true
    )
    UserDetail findUserDetailByUsername(@Param("username") String username);

    @Query(
            value=
                    "SELECT u.id,u.username, u.email, u.enabled," +
                    " u.wechat, u.create_time, u.update_time," +
                    " p.mobile, p.sex, p.name, p.avatar,      "+
                    "  u.status, p.policeno, p.position,       "+
                    "  p.identity, p.remark, p.sort, so.orgid, so.orgname  "+
                    "FROM user u                                          "+
                    "  LEFT JOIN profile p ON u.id = p.user_id            "+
                    "  LEFT JOIN sel_user_organization so ON so.id = u.id "+
                    "  LEFT JOIN user_org_view ov ON u.id = ov.user_id    "+
                    "  LEFT JOIN user_role r ON u.id = r.user_id          "+
                    "WHERE u.status != 999                                 "+
                    "      AND r.role_id = 2                               "+
                    "      AND ov.appid = :corpId                     ",
            nativeQuery = true
    )
    List<UserDetail> findAdmin(@Param("corpId") String corpId);

    @Query(
            value=
                    "SELECT DISTINCT u.id,u.username, u.email, u.enabled,  "+
                    "u.wechat, u.create_time, u.update_time,               "+
                    "p.mobile, p.sex, p.name, p.avatar,                    "+
                    " u.status, p.policeno, p.position,                    "+
                    " p.identity, p.remark, p.sort, so.orgid, so.orgname   "+
                    "FROM user u                                           "+
                    "LEFT JOIN profile p ON u.id = p.user_id               "+
                    "LEFT JOIN user_org_view v ON  u.id = v.user_id        "+
                    "LEFT JOIN sel_user_organization so ON so.id = u.id    "+
                    "WHERE v.appid = :corpId                           "+
                    "AND u.status != 999                                    "+
                    "ORDER BY u.id                                         ",
            nativeQuery = true
    )
    List<UserDetail> findAllByAppid(@Param("corpId") String corpId);

    @Query(
            value=
                    "SELECT DISTINCT u.id,u.username, u.email, u.enabled,  "+
                    "u.wechat, u.create_time, u.update_time,               "+
                    "p.mobile, p.sex, p.name, p.avatar,                    "+
                    " u.status, p.policeno, p.position,                    "+
                    " p.identity, p.remark, p.sort, so.orgid, so.orgname   "+
                    "FROM user u                                           "+
                    "  LEFT JOIN profile p ON u.id = p.user_id             "+
                    "  LEFT JOIN sel_user_organization so ON so.id = u.id  "+
                    "  LEFT JOIN org_user ou ON ou.user_id = u.id          "+
                    "WHERE ou.org_id = :orgId                               "+
                    "      AND u.status != 999                             "+
                    "ORDER BY u.id                                         ",
            nativeQuery = true
    )
    List<UserDetail> findByOrgId(@Param("orgId") Long orgId);

}
