package com.yj.web;

import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.MenuService;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.ReSult;
import com.yj.pojo.system.user.UserDto;
import com.yj.pojo.system.user.UserSearchDto;
import com.yj.pojo.system.user.UserUpPasswordDto;
import com.yj.security.MyGrantedAuthority;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(value = "/user", tags = "user", description = "用户接口")
public class UserApi {
    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "/user/add", nickname = "添加用户", notes = "添加用户")
    @RequestMapping(value = "/user/add", method = RequestMethod.POST, produces = {"application/json"})
    ReSult addUser(@Valid @RequestBody UserDto userDto, @SessionAttribute(name = "user")UserDetail user) throws Exception {
        userDto.setClient(user.getClient());
        return userService.addUser(userDto);
    }

    @ApiOperation(value = "/user/update-password", nickname = "用户自主修改密码", notes = "用户自主修改密码")
    @RequestMapping(value = "/user/update-password", method = RequestMethod.POST, produces = {"application/json"})
    ReSult updatePasswordUser(@Valid UserUpPasswordDto dto, @SessionAttribute(name = "user")UserDetail user,
                              @SessionAttribute(name = "userName") String userName) throws Exception {
        return userService.userUpPassword(dto,userName,1L);
    }

    @ApiOperation(value = "/user/update-password2", nickname = "更改他人用户密码", notes = "更改他人用户密码")
    @RequestMapping(value = "/user/update-password2", method = RequestMethod.POST, produces = {"application/json"})
    ReSult updatePasswordUser2(@Valid UserUpPasswordDto dto, @SessionAttribute(name = "user")UserDetail user,
                              @SessionAttribute(name = "userName") String userName) throws Exception {
        return userService.userUpPassword(dto,userName,null);
    }

    @ApiOperation(value = "/user/update", nickname = "修改用户资料", notes = "修改用户资料")
    @RequestMapping(value = "/user/update", method = RequestMethod.POST, produces = {"application/json"})
    ReSult updateUser(@RequestBody UserDetail userDetail) throws Exception {
        return userService.updateUser(userDetail);
    }

    @ApiOperation(value = "/user/search", nickname = "查询用户资料", notes = "查询用户资料")
    @RequestMapping(value = "/user/search", method = RequestMethod.GET, produces = {"application/json"})
    ReSult searchUser(UserSearchDto dto, Pageable pageable,@SessionAttribute(name = "user") UserDetail userDetail) throws Exception {
        dto.setClient(userDetail.getClient());
        return userService.searchUser(dto,pageable);
    }

    @ApiOperation(value = "/user/menu/search", nickname = "查询用户菜单", notes = "查询用户菜单")
    @RequestMapping(value = "/user/menu/search", method = RequestMethod.GET, produces = {"application/json"})
    ReSult searchUserMenu(@SessionAttribute(name = "user") UserDetail userDetail) throws Exception {
        SecurityContext context=SecurityContextHolder.getContext();
        Authentication au= context.getAuthentication();
        List<MyGrantedAuthority> list= (List<MyGrantedAuthority>) au.getAuthorities();
        return menuService.searchUserMenu(list);
    }
}
