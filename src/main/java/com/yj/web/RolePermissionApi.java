package com.yj.web;

import com.yj.domain.user.model.RolePermission;
import com.yj.domain.user.model.RolePermissionDto;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.RolePermissionService;
import com.yj.pojo.ReSult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/role-permission", tags = "/role-permission", description = "角色授权")
public class RolePermissionApi {

    @Autowired
    private RolePermissionService rolePermissionService;

    @ApiOperation(value = "/role-permission/save", nickname = "角色权限", notes = "角色权限")
    @RequestMapping(value = "/role-permission/save", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('boss', '')")
    ReSult createPermission(@RequestBody RolePermissionDto dto, @SessionAttribute(name = "user") UserDetail user) {
        return rolePermissionService.addRolePermission(dto,user);
    }
}
