package com.yj.domain.user.service;

import com.yj.domain.user.model.UserDetail;
import com.yj.pojo.MenuDto;
import com.yj.pojo.MenuUpdateDto;
import com.yj.pojo.ReSult;

import javax.validation.Valid;

public interface MenuService {
    ReSult addMenu( MenuDto menuDto, UserDetail user);

    ReSult updateMenu( MenuUpdateDto dto, UserDetail user);
}
