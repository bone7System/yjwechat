package com.yj.domain.user.service;


import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.UserRepository;
import com.yj.domain.user.repository.UserSpecs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Cacheable(value = "user", key = "'houseCode_'")
    public List testList() {
        System.out.println("11111111111");
        List list = new ArrayList();
        User u = new User();
        u.setId(1L);
        u.setUsername("2");

        User u2 = new User();
        u2.setId(1L);
        u2.setUsername("2");

        User u3 = new User();
        u3.setId(1L);
        u3.setUsername("2");
        list.add(u);
        list.add(u2);
        list.add(u3);
        return list;

    }

    //--------Begin--CUD----------
    @Override
    public User save(User user) throws DataAccessException {
        User result = this.userRepository.save(user);
        return result;
    }
    //---------End---CUD----------

    @Override
    public User findById(Long id) throws DataAccessException{
        User result = this.userRepository.findOne(id);
        return result;
    }

    @Override
    public User findByUsername(String username)throws DataAccessException {
        User result = this.userRepository.findByUsername(username);
        return result;
    }

    @Override
    public User findByWechatAndAppid(String wechat, String appid) throws DataAccessException {
        return  this.userRepository.findByWechatAndAppid(wechat, appid);
    }

    @Override
    public User updatePassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public List<Long> findOrgIdsByUserId(Long userId) {
        return null;
    }


    @Override
    public Page<User> findAll(Pageable pageable) throws DataAccessException{
        Page<User> userPage = this.userRepository.findAll(pageable);
        return userPage;
    }

    @Override
    public Page<User> search(String searchString, Pageable pageable) throws DataAccessException {
        Page<User> userPage = this.userRepository.findAll(UserSpecs.setQuery(searchString),pageable);
        return userPage;
    }

    @Override
    public UserDetail findUserDetail(Long userId) {
        return null;
    }


}
