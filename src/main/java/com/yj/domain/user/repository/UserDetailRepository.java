package com.yj.domain.user.repository;

import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> ,JpaSpecificationExecutor<UserDetail> {

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

   UserDetail findByUserId(Long userId);

   List<UserDetail> findByClient(Long id);

}
