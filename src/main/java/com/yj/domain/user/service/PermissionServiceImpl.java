package com.yj.domain.user.service;

import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.PermissionRepository;
import com.yj.exception.YjException;
import com.yj.pojo.system.permission.PermissionDto;
import com.yj.pojo.ReSult;
import com.yj.utils.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermissionServiceImpl implements  PermissionService{

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public ReSult addPermission(PermissionDto permissionDto, UserDetail user) {
        Permission ps = new Permission();
        BeanUtils.copyProperties(permissionDto,ps);
        ps.setCreateTime(new Date());
        ps.setCreateUser(user.getUserId());

        permissionRepository.saveAndFlush(ps);
        ps.setPath("/"+ps.getId());

        if(ps.getParentId()!=null){
            Permission permission=  permissionRepository.getOne(ps.getParentId());
            ps.setPath(permission.getPath()+"/"+ps.getId());

        }
        permissionRepository.saveAndFlush(ps);
        //ps.setPath();
        return ReSult.success();
    }

    @Override
    public ReSult updatePermission(PermissionDto permissionDto, UserDetail user) {
       Permission permission= permissionRepository.getOne(permissionDto.getId());
       if(!StringUtils.isEmpty(permissionDto.getDescription())){
           permission.setDescription(permissionDto.getDescription());
       }
        if(permissionDto.getMenuId()!=null){
            permission.setMenuId(permissionDto.getMenuId());
        }
        if(!StringUtils.isEmpty(permissionDto.getPermission())){
            permission.setPermission(permissionDto.getPermission());
        }

        permissionRepository.saveAndFlush(permission);
        return ReSult.success();
    }

    @Override
    public ReSult deletePermission(Long id) throws YjException {
       List list= permissionRepository.findByParentId(id);
       if(list!=null&&list.size()>0){
           throw new YjException("已存在子节点,不能删除");
       }
       permissionRepository.delete(permissionRepository.getOne(id));
        return ReSult.success() ;
    }
}
