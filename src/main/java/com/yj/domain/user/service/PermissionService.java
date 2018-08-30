package com.yj.domain.user.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.exception.YjException;
import com.yj.pojo.system.permission.PermissionDto;
import com.yj.pojo.ReSult;

public interface PermissionService {

    ReSult addPermission(PermissionDto permissionDto, UserDetail user);

    ReSult updatePermission( PermissionDto permissionDto, UserDetail user);

    ReSult deletePermission(Long id) throws YjException;
}
