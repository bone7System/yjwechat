package com.yj.domain.user.service;

import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {


    List testList();
    //--------Begin--CUD----------
    User save(User user) throws DataAccessException;
    //---------End---CUD----------

    User findById(Long id) throws DataAccessException;
    User findByUsername(String username) throws DataAccessException;
    User findByWechatAndAppid(String wechat, String appid) throws DataAccessException;
    User updatePassword(User user, String password);
    List<Long> findOrgIdsByUserId(Long userId);
    Page<User> findAll(Pageable pageable) throws DataAccessException;
    // 根据 username, 手机号, 或邮箱搜索
    Page<User> search(String searchString, Pageable pageable) throws DataAccessException;

    UserDetail findUserDetail(Long userId);





}
