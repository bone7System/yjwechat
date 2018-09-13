package com.yj.web;

import com.yj.domain.user.model.Dept;
import com.yj.domain.user.model.Role;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.*;
import com.yj.exception.YjException;
import com.yj.pojo.*;
import com.yj.pojo.system.menu.MenuDto;
import com.yj.pojo.system.menu.MenuUpdateDto;
import com.yj.pojo.system.permission.PermissionDto;
import com.yj.pojo.system.role.RoleDto;
import com.yj.pojo.system.role.RoleSearchDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController
@Api(value = "/system", tags = "system", description = "超级管理员")
public class SystemApi {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private DeptService deptService;

    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "/role/add", nickname = "添加角色", notes = "添加角色")
    @RequestMapping(value = "/role/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission({'admin','boss'}, '')")
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
    @PreAuthorize("hasPermission({'admin','boss'}, '')")
    ReSult updateRole(@Valid @RequestBody RoleDto roleDto) {
        Role role= roleService.update(roleDto);
        if(role!=null){
            return new ReSult("修改成功");
        }
        return new ReSult(500L,"修改失败",null);
    }


    @ApiOperation(value = "/role/search", nickname = "获取角色", notes = "获取角色")
    @RequestMapping(value = "/role/search", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission({'admin','boss'}, '')")
    ReSult searchRole(RoleSearchDto roleDto) {
        return roleService.seachRole(roleDto);
    }

    @ApiOperation(value = "/role/role-delete", nickname = "删除角色", notes = "删除角色")
    @RequestMapping(value = "/role/role-delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission({'admin','boss'}, '')")
    ReSult updateRole(Long roleId) {

        return roleService.deleteRole(roleId);
    }

    @ApiOperation(value = "/permisstion/add", nickname = "添加权限", notes = "添加权限")
    @RequestMapping(value = "/permisstion/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createPermission(@Valid @RequestBody PermissionDto permissionDto, @SessionAttribute(name = "user") UserDetail user) {

        return permissionService.addPermission(permissionDto,user);

    }

    @ApiOperation(value = "/menu/add", nickname = "添加菜单", notes = "添加菜单")
    @RequestMapping(value = "/menu/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createMenu(@Valid @RequestBody MenuDto menuDto, @SessionAttribute(name = "user") UserDetail user) {

        return menuService.addMenu(menuDto,user);

    }

    @ApiOperation(value = "/menu/update", nickname = "修改菜单", notes = "修改菜单")
    @RequestMapping(value = "/menu/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updateMenu(@Valid @RequestBody MenuUpdateDto dto, @SessionAttribute(name = "user") UserDetail user) {

        return menuService.updateMenu(dto,user);

    }

    @ApiOperation(value = "/menu/get", nickname = "获取菜单", notes = "获取菜单")
    @RequestMapping(value = "/menu/get", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getMenu() {

        return menuService.getMenu();

    }

    @ApiOperation(value = "/menu/getById", nickname = "获取菜单", notes = "获取菜单")
    @RequestMapping(value = "/menu/getById", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult getMenuById(@RequestParam Long id) {
        return menuService.getById(id);

    }

    @ApiOperation(value = "/menu/delete", nickname = "删除菜单", notes = "删除菜单")
    @RequestMapping(value = "/menu/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult menuDelete(@RequestBody Map<String,Object> params) {
        Integer id= (Integer) params.get("id");
        return menuService.deleteById(Long.parseLong(id+""));

    }

    @ApiOperation(value = "/permisstion/update", nickname = "修改权限", notes = "修改权限")
    @RequestMapping(value = "/permisstion/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updatePermission( @RequestBody PermissionDto permissionDto, @SessionAttribute(name = "user") UserDetail user) {
        return permissionService.updatePermission(permissionDto,user);
    }


    @ApiOperation(value = "/permisstion/delete", nickname = "删除权限", notes = "删除权限")
    @RequestMapping(value = "/permisstion/delete", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult deletePermission( Long id) throws YjException {
        return permissionService.deletePermission(id);
    }

    @ApiOperation(value = "/dept/add", nickname = "添加门店", notes = "添加门店")
    @RequestMapping(value = "/dept/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createPermission( @RequestBody Dept dept, @SessionAttribute(name = "user") UserDetail user) {

        return deptService.addDept(dept,user);

    }

    @ApiOperation(value = "/dept/update", nickname = "修改门店", notes = "修改门店")
    @RequestMapping(value = "/dept/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updatePermission( @RequestBody Dept dept, @SessionAttribute(name = "user") UserDetail user) {
        return deptService.updateDept(dept,user);

    }
    @ApiOperation(value = "/dept/delete", nickname = "删除门店", notes = "删除门店")
    @RequestMapping(value = "/dept/delete", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult deletePermission( Long id , @SessionAttribute(name = "user") UserDetail user) throws YjException {
        return deptService.deleteDept(id,user);
    }



}
