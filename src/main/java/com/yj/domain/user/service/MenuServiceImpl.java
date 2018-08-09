package com.yj.domain.user.service;

import com.yj.domain.user.model.Menu;
import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.MenuRepository;
import com.yj.pojo.MenuDto;
import com.yj.pojo.MenuUpdateDto;
import com.yj.pojo.ReSult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;

@Service
public class MenuServiceImpl implements  MenuService{
    @Autowired
    private MenuRepository menuRepository;
    @Override
    public ReSult addMenu(@Valid MenuDto menuDto, UserDetail user) {

        Menu ps = new Menu();
        BeanUtils.copyProperties(menuDto,ps);
        ps.setCreateTime(new Date());
        menuRepository.saveAndFlush(ps);
        ps.setPath("/"+ps.getId());

        if(ps.getParentId()!=null){
            Menu menu=  menuRepository.getOne(ps.getParentId());
            ps.setPath(menu.getPath()+"/"+ps.getId());

        }
        menuRepository.saveAndFlush(ps);
        //ps.setPath();
        return ReSult.success();
    }

    @Override
    public ReSult updateMenu(MenuUpdateDto dto, UserDetail user) {

        Menu menu= menuRepository.getOne(dto.getId());
        if(menu!=null){
            BeanUtils.copyProperties(dto,menu);
            menuRepository.save(menu);
            return ReSult.success();
        }
        return ReSult.error(500L,"修改失败");
    }
}
