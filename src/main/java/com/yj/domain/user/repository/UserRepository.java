package com.yj.domain.user.repository;

import com.yj.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long>,JpaSpecificationExecutor<User> {
    @Query(value =
            "SELECT u.*                    "+
            "FROM user u                   "+
            "WHERE u.username = :username and u.status != 999  "
            , nativeQuery = true)
    User findByUsername(@Param("username") String username);

    @Query(value =
            "SELECT u.*                    "+
            "FROM user u                   "+
            "LEFT JOIN user_org_view v on v.user_id = u.id " +
            "WHERE u.username = :username and u.status = 1 and v.appid = :appid"
            , nativeQuery = true)
    User findByUsernameAndAppid(@Param("username") String username, @Param("appid") String appid);
    @Query(value =
            "SELECT u.*                    "+
            "FROM user u                   "+
            "LEFT JOIN user_org_view v on v.user_id = u.id " +
            "WHERE u.wechat = :wechat and u.status = 1 and v.appid = :appid"
            , nativeQuery = true)
    User findByWechatAndAppid(@Param("wechat")String wechat, @Param("appid") String appid);
    User findById(Long id);

    @Modifying
    @Query(value = "UPDATE user U SET U.STATUS = :status WHERE U.ID = :userId", nativeQuery = true)
    Integer updateStatusByUserId(@Param("status") Long status, @Param("userId") Long userId);

    @Query(value =
            "SELECT u.*                    "+
            "FROM user u                   "+
            "WHERE u.status = 1            "+
            "AND u.id IN (SELECT user_id   "+
            "FROM org_user                 "+
            "WHERE org_id = :orgId)       "
            , nativeQuery = true)
    List<User> findByOrgId(@Param("orgId") Long orgId);


    @Query(
            value =
            "SELECT u.*                   "+
            "FROM user u                  "+
            "LEFT JOIN org_car_manage om  "+
            "ON u.id = om.manage_user_id  "+
            "WHERE om.org_id = :orgId     ",
            nativeQuery = true
    )
    List<User> findChargehandByOrgId(@Param("orgId") Long orgId);

    @Query(
            value =
                    "SELECT u.*                   "+
                    "FROM user u                  "+
                    "LEFT JOIN org_car_responsible om  "+
                    "ON u.id = om.responsible_user_id  "+
                    "WHERE om.org_id = :orgId     ",
            nativeQuery = true
    )
    List<User> findLeaderByOrgId(@Param("orgId") Long orgId);
}
