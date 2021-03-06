package com.yj.web;


import com.yj.domain.user.model.Menu;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.service.MenuService;
import com.yj.domain.user.service.UserService;
import com.yj.pojo.ReSult;
import com.yj.pojo.login.LoginCredencial;
import com.yj.pojo.login.LoginResponse;
import com.yj.security.MyGrantedAuthority;
import com.yj.security.MyUserDetailsService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


@RestController
@Api(value = "/login", tags = "Login", description = "登录接口")
public class LoginApi {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private UserService userService;
    @Autowired
    private MenuService menuService;

    @ApiOperation(value = "/wechat/login", nickname = "微信端登陆", notes = "微信端登陆")
    @RequestMapping(value = "/wechat/login", method = RequestMethod.POST, produces = {"application/json"})
    //@PreAuthorize("hasPermission('wechat', 'login')")
    LoginResponse wechatLogin(HttpServletRequest request, @RequestBody LoginCredencial credencial) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(credencial.getUserName(), credencial.getPassWord());
        // Authenticate the user
        Authentication authentication = authenticationProvider.authenticate(token);
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(authentication);

        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(RequestContextHolder.currentRequestAttributes().getSessionId());
        MyUserDetailsService.MyUserDetail detail=
                (MyUserDetailsService.MyUserDetail) authentication.getPrincipal();
        //权限
        List<MyGrantedAuthority> list= (List<MyGrantedAuthority>) authentication.getAuthorities();

        UserDetail user= userService.findByUserId(detail.getUserId());
        session.setAttribute("user", user);
        session.setAttribute("userName",credencial.getUserName());

        ReSult reSult= menuService.searchUserMenu(list);
        loginResponse.setMens((List<Menu>) reSult.getData());
        session.setAttribute("menus",reSult.getData());
        loginResponse.setUser(user);
        return loginResponse;
    }

    @ApiOperation(value = "/logout", nickname = "退出登陆", notes = "退出登陆")
    @RequestMapping(value = "/logout", method = RequestMethod.POST, produces = {"application/json"})
    ReSult logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return ReSult.error(400L,"退出登录失败");
        }
        session.invalidate();
         return ReSult.success();
    }









}
