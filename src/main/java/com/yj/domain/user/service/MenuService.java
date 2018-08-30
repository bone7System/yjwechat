package com.yj.domain.user.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.system.menu.MenuDto;
import com.yj.pojo.system.menu.MenuUpdateDto;
import com.yj.pojo.ReSult;
import com.yj.security.MyGrantedAuthority;

import java.util.List;


public interface MenuService {
    ReSult addMenu( MenuDto menuDto, UserDetail user);

    ReSult updateMenu( MenuUpdateDto dto, UserDetail user);

    ReSult getMenu();

    ReSult searchUserMenu(List<MyGrantedAuthority> list, UserDetail userDetail);

}
