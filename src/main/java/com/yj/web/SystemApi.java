package com.yj.web;

import com.yj.domain.user.model.Role;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.OrganizationService;
import com.yj.domain.user.service.RoleService;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.ReSult;
import com.yj.pojo.RoleDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(value = "/system", tags = "system", description = "超级管理员")
public class SystemApi {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;


    @Autowired
    private RoleService roleService;

    @ApiOperation(value = "/system", nickname = "创建根组织", notes = "创建根组织")
    @RequestMapping(value = "/system", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission({'system','adc'},'')")
    void createRootOrg() {

    }

    @ApiOperation(value = "/role/role-add", nickname = "添加角色", notes = "添加角色")
    @RequestMapping(value = "/role/role-add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult createRole(@Valid @RequestBody RoleDto roleDto, @SessionAttribute(name = "user") UserDetail user) {

        roleDto.setCreateUser(user.getUserId());
        Role role= roleService.save(roleDto);
        if(role!=null){
            return new ReSult("操作成功");
        }
        return new ReSult(500L,"操作失败",null);
    }

    @ApiOperation(value = "/role/role-update", nickname = "修改角色", notes = "添分角色")
    @RequestMapping(value = "/role/role-update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('admin', '')")
    ReSult updateRole(@Valid @RequestBody RoleDto roleDto) {


        Role role= roleService.update(roleDto);
        if(role!=null){
            return new ReSult("修改成功");
        }
        return new ReSult(500L,"修改失败",null);
    }

}
