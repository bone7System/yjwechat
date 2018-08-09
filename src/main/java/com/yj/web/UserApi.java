package com.yj.web;

import com.yj.domain.user.model.User;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.ReSult;
import com.yj.pojo.UserDto;
import com.yj.pojo.UserSearchDto;
import com.yj.pojo.UserUpPasswordDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@Api(value = "/user", tags = "user", description = "用户接口")
public class UserApi {
    @Autowired
    private UserService userService;
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
}
