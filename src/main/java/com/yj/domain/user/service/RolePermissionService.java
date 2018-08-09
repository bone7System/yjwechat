package com.yj.domain.user.service;

import com.yj.domain.user.model.RolePermissionDto;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.ReSult;

public interface RolePermissionService {
    ReSult addRolePermission(RolePermissionDto list, UserDetail user);
}
