package com.yj.domain.user.service;

import com.yj.domain.user.model.Menu;
import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.system.menu.MenuDto;
import com.yj.pojo.system.menu.MenuUpdateDto;
import com.yj.pojo.ReSult;
import com.yj.security.MyGrantedAuthority;

import java.util.List;
import java.util.Optional;


public interface MenuService {
    ReSult addMenu( MenuDto menuDto, UserDetail user);

    ReSult updateMenu( MenuUpdateDto dto, UserDetail user);

    ReSult getMenu();

    ReSult searchUserMenu(List<MyGrantedAuthority> list);

    ReSult getById(Long id);

    ReSult deleteById(Long id);
}
