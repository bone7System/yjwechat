package com.yj.domain.user.service;

import com.yj.domain.user.model.Menu;
import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.MenuRepository;
import com.yj.domain.user.repository.PermissionRepository;
import com.yj.pojo.system.menu.MenuDto;
import com.yj.pojo.system.menu.MenuUpdateDto;
import com.yj.pojo.ReSult;
import com.yj.security.MyGrantedAuthority;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 */
@Service
public class MenuServiceImpl implements  MenuService{
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    @Override
    public ReSult addMenu(@Valid MenuDto menuDto, UserDetail user) {

        Menu ps = new Menu();
        BeanUtils.copyProperties(menuDto,ps);
        ps.setCreateTime(new Date());
        menuRepository.saveAndFlush(ps);
        ps.setPath("/"+ps.getId());
        ps.setDelFlag(1L);
        if(ps.getParentId()!=null){
            Menu menu=  menuRepository.findById(ps.getParentId()).get();
            ps.setPath(menu.getPath()+"/"+ps.getId());

        }
        menuRepository.saveAndFlush(ps);
        //ps.setPath();
        return ReSult.success();
    }

    @Override
    public ReSult updateMenu(MenuUpdateDto dto, UserDetail user) {

        Menu menu= menuRepository.findById(dto.getId()).get();
        if(menu!=null){
            BeanUtils.copyProperties(dto,menu, com.yj.utils.StringUtils.getNullPropertyNames(dto));
            menuRepository.save(menu);
            return ReSult.success();
        }
        return ReSult.error(500L,"修改失败");
    }

    @Override
    public ReSult getMenu() {
        List list=menuRepository.findAll();
        return ReSult.success(list);
    }

    @Override
    public ReSult searchUserMenu(List<MyGrantedAuthority> list) {
        if(list!=null&&list.size()>0){
            MyGrantedAuthority ma=list.get(0);
            List<Permission> pms= permissionRepository.findByPermissionIn(ma.getPermissions());
            if(pms!=null){
                Set<Long> menuIds=pms.stream().map(m->{

                    return  m.getMenuId();
                }).distinct().collect(Collectors.toSet());
                System.out.println(menuIds.contains("test1"));
               List<Menu> newMenu= menuRepository.findByIdIn(menuIds);
               return menuList(newMenu);
            }
        }
        return ReSult.error(500L,"未定义菜单");
    }

    @Override
    public ReSult getById(Long id) {
        Optional<Menu> po = menuRepository.findById(id);
        return ReSult.success(po);
    }

    @Override
    public ReSult deleteById(Long id) {
        Optional<Menu> po = menuRepository.findById(id);
        po.get().setDelFlag(-1L);
        menuRepository.save(po.get());
        return ReSult.success();
    }

    private ReSult menuList(List<Menu> newMenu) {
        Set<Long> ids=new HashSet<>();
        if(newMenu!=null){

            newMenu.stream().forEach(menu -> {
                String[] pathArr=menu.getPath().split("/");
                if(pathArr.length>0){
                    for (String id: pathArr
                         ) {
                        if(!StringUtils.isEmpty(id)){
                                ids.add(Long.parseLong(id));
                        }
                    }
                }
            });
            List list=menuRepository.findByIdIn(ids);
            return ReSult.success(list);
        }
       return ReSult.error(500L,"未定义菜单");
    }


}
