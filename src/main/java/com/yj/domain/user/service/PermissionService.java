package com.yj.domain.user.service;

import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.PermissionDto;
import com.yj.pojo.ReSult;

public interface PermissionService {

    ReSult addPermission(PermissionDto permissionDto, UserDetail user);
}
