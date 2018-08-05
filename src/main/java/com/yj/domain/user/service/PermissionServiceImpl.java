package com.yj.domain.user.service;

import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.PermissionRepository;
import com.yj.pojo.PermissionDto;
import com.yj.pojo.ReSult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        if(ps.getPrantId()!=null){
          Permission permission=  permissionRepository.getOne(ps.getPrantId());

        }
        ps.setPath();
        return null;
    }
}
