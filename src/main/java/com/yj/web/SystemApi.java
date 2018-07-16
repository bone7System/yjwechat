package com.yj.web;

import com.yj.domain.user.service.OrganizationService;
import com.yj.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Api(value = "/system", tags = "system", description = "超级管理员")
public class SystemApi {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;


    @ApiOperation(value = "/system", nickname = "创建根组织", notes = "创建根组织")
    @RequestMapping(value = "/system", method = RequestMethod.GET, produces = {"application/json"})
    @PreAuthorize("hasPermission({'system','adc'}, 'admin')")
    void createRootOrg() {

    }

}
