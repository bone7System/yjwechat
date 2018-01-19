package com.yj.web;

import com.yj.domain.user.service.OrganizationService;
import com.yj.domain.user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by tao.huang on 2017/6/20.
 */
@RestController
@Api(value = "/system", tags = "system", description = "超级管理员")
public class SystemApi {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private UserService userService;


//    @ApiOperation(value = "/system/create-root-orgization", nickname = "创建根组织", notes = "创建根组织")
//    @RequestMapping(value = "/system/create-root-orgization", method = RequestMethod.POST, produces = {"application/json"})
//    @PreAuthorize("hasPermission('system', 'admin')")
//    BooleanResult createRootOrg(@Valid @RequestBody RootOrganizationDtoC rootOrganizationDtoC) {
//
//    }

}
