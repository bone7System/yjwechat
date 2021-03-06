package com.yj.security;

import com.yj.domain.user.model.User;
import com.yj.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;

@Component
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        String username = authentication.getName();
        //多个角色是数组 单个是 String类型
        if(targetDomainObject instanceof Collection){

        }else if(targetDomainObject instanceof  String){

        }



        return true;
//        User user = userService.findByUsername(username);
//        if (user == null) {
//            throw  new UsernameNotFoundException("用户不存在");
//        }
//        return securityService.authorized(user.getId(), targetDomainObject.toString(), permission.toString());
    }
    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        // not supported
        return false;
    }
}