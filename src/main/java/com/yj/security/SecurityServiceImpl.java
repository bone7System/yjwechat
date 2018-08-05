package com.yj.security;

import com.yj.domain.user.model.Permission;
import com.yj.domain.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityServiceImpl implements SecurityService {
    @Autowired
    RoleService roleService;
    @Override
    public boolean authorized(Long userId, Object targetDomainObject, Object permission) {
        List<Permission> permissions = null ;//roleService.getUserPermissions(userId);
        for (Permission p:permissions) {

        }
        return false;
    }
}
