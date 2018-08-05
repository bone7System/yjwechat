package com.yj.web;

import com.yj.domain.user.model.Role;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.PermissionService;
import com.yj.domain.user.service.RoleService;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.PermissionDto;
import com.yj.pojo.ReSult;
import com.yj.pojo.RoleDto;
import com.yj.pojo.RoleSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "/system", tags = "system", description = "超级管理员")
public class SystemApi {

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @ApiOperation(value = "/system", nickname = "创建根组织", notes = "创建根组织")
    @RequestMapping(value = "/system", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission({'system','adc'},'')")
    void createRootOrg() {

    }

    @ApiOperation(value = "/role/add", nickname = "添加角色", notes = "添加角色")
    @RequestMapping(value = "/role/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createRole(@Valid @RequestBody RoleDto roleDto, @SessionAttribute(name = "user") UserDetail user) {

        roleDto.setCreateUser(user.getUserId());
        Role role= roleService.save(roleDto);
        if(role!=null){
            return ReSult.success(null);
        }
        return  ReSult.error("添加失败",null);
    }

    @ApiOperation(value = "/role/update", nickname = "修改角色", notes = "修改角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updateRole(@Valid @RequestBody RoleDto roleDto) {
        Role role= roleService.update(roleDto);
        if(role!=null){
            return new ReSult("修改成功");
        }
        return new ReSult(500L,"修改失败",null);
    }


    @ApiOperation(value = "/role/search", nickname = "获取角色", notes = "获取角色")
    @RequestMapping(value = "/role/search", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult searchRole(RoleSearchDto roleDto) {
        return roleService.seachRole(roleDto);
    }

    @ApiOperation(value = "/role/role-delete", nickname = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/role/role-delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updateRole(Long roleId) {

        return roleService.deleteRole(roleId);
    }

    @ApiOperation(value = "/permisstion/add", nickname = "添加权限", notes = "添加权限")
    @RequestMapping(value = "/permisstion/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createRole(@Valid @RequestBody PermissionDto permissionDto, @SessionAttribute(name = "user") UserDetail user) {


        return permissionService.addPermission(permissionDto,user);

    }

}
