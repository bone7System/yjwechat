package com.yj.web;


import com.yj.domain.user.model.User;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.LoginCredencial;
import com.yj.pojo.LoginResponse;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


@RestController
@Api(value = "/login", tags = "Login", description = "登录接口")
public class LoginApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @ApiOperation(value = "/wechat/login", nickname = "微信端登陆", notes = "微信端登陆")
    @RequestMapping(value = "/wechat/login", method = RequestMethod.POST, produces = {"application/json"})
    //@PreAuthorize("hasPermission('wechat', 'login')")
    LoginResponse wechatLogin(HttpServletRequest request, @RequestBody LoginCredencial credencial) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credencial.getUsername(), credencial.getPassword());

        // Authenticate the user
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(RequestContextHolder.currentRequestAttributes().getSessionId());
       // RequestContextHolder.currentRequestAttributes().setAttribute("user","zwh455656666",1);
        session.setAttribute("user", "zwh");
        return loginResponse;
    }
    @ApiOperation(value = "/wechat/get", nickname = "测试获取session", notes = "测试获取session")
    @RequestMapping(value = "/wechat/get", method = RequestMethod.POST, produces = {"application/json"})
    String test(@SessionAttribute("user") String user) {

        System.out.println(user);
        return user;
    }


    @ApiOperation(value = "/wechat/serch", nickname = "测试获取session", notes = "测试获取session")
    @RequestMapping(value = "/wechat/serch", method = RequestMethod.POST, produces = {"application/json"})
    List testSeach() {
            return userService.testList();
    }









}
