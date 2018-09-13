package com.yj.domain.user.service;


import com.google.common.collect.Lists;
import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.model.UserRole;
import com.yj.domain.user.repository.UserDetailRepository;
import com.yj.domain.user.repository.UserRepository;
import com.yj.domain.user.repository.UserRoleRepository;
import com.yj.pojo.ReSult;
import com.yj.pojo.system.user.UserDto;
import com.yj.pojo.system.user.UserSearchDto;
import com.yj.pojo.system.user.UserUpPasswordDto;
import com.yj.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public User getUserByUserName(String userName) {

        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetail findByUserId(Long userId) {
        return userDetailRepository.findByUserId(userId);
    }

    @Override
    public ReSult addUser(UserDto userDto) throws Exception {
        //验证密码
        checkUser(userDto);
        User login=new User();
        BeanUtils.copyProperties(userDto,login);
        if(login.getTimeb()==null){
            login.setTimeb(new Date());
        }
        if(login.getTimee()==null){
            //默认 9999-12-31
            login.setTimee(new Date(253402185600000L));
        }
        login.setPassWord(passwordEncoder.encode(login.getPassWord()));
        //保存登录用户
        userRepository.save(login);

        UserDetail userDetail = new UserDetail();
        BeanUtils.copyProperties(userDto,userDetail);
        userDetail.setUserId(login.getId());

        userDetailRepository.saveAndFlush(userDetail);

        //为用户设置角色
        if(userDto.getRoleIds()!=null){
            userDto.getRoleIds().stream().forEach(ix->{
                UserRole ur=new UserRole();
                ur.setClient(userDto.getClient());
                ur.setRoleId(ix);
                ur.setUserId(login.getId());
                userRoleRepository.save(ur);
            });
        }

        return new ReSult(200L,"用户保存成功",null);
    }

    @Override
    public ReSult userUpPassword(UserUpPasswordDto dto,String userName,Long isMe) throws Exception {
        checkUser(dto,userName,isMe);
        User u=  userRepository.findByUserName(userName);
        u.setPassWord(passwordEncoder.encode(dto.getPassWord()));
        return null;
    }

    @Override
    public ReSult updateUser(UserDetail userDetail) {
        return null;
    }

    @Override
    public ReSult searchUser(UserSearchDto dto,Pageable pageable) {
       Page page= userDetailRepository.findAll(new Specification<UserDetail>() {
            @Nullable
            @Override
            public Predicate toPredicate(Root<UserDetail> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = Lists.newArrayList();
                //client 是必须的
                 predicates.add(cb.equal(root.get("client"),dto.getClient()));

                 if(!StringUtils.isEmpty(dto.getName())){
                     predicates.add(cb.like(root.get("name"),dto.getName()));

                 }
                if(!StringUtils.isEmpty(dto.getPhone())){
                    predicates.add(cb.like(root.get("phone"),dto.getPhone()));

                }
                return query.where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
            }
        },pageable);
        return ReSult.success(page);
    }

    private void checkUser(UserUpPasswordDto dto,String userName,Long isMe) throws Exception {
        if(!dto.getPassWord().equals(dto.getPassWord2())){
            throw new Exception("俩次密码不一致");
        }
        if(isMe!=null) {
            if (!dto.getUserName().equals(userName)) {
                throw new Exception("用户名密不匹配");
            }
        }
    }

    void checkUser(UserDto dto) throws Exception {
        if(!dto.getPassWord().equals(dto.getPassWord2())){
            throw new Exception("俩次密码不一致");
        }
    }




}
