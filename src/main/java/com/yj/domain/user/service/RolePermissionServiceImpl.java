package com.yj.domain.user.service;

import com.yj.domain.user.model.RolePermission;
import com.yj.domain.user.model.RolePermissionDto;
import com.yj.domain.user.model.UserDetail;
import com.yj.domain.user.repository.RolePermissionRepository;
import com.yj.pojo.ReSult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolePermissionServiceImpl implements RolePermissionService {
    @Autowired
    RolePermissionRepository rolePermissionRepository;

    @Override
    public ReSult addRolePermission(RolePermissionDto dto, UserDetail user) {
        List<RolePermission> list= rolePermissionRepository.findByRoleIdAndClient(dto.getRoleId(),user.getClient());
        if(list!=null && dto.getPermissions()!=null&&dto.getPermissions().size()>0){
            rolePermissionRepository.deleteAll(list);
        }

        dto.getPermissions().stream().forEach(item->{
            RolePermission rp = new RolePermission();
            rp.setPermissionId(item);
            rp.setRoleId(dto.getRoleId());
            rp.setClient(user.getClient());
            rolePermissionRepository.save(rp);
        });
        return ReSult.success();
    }

    @Override
    public ReSult selectRolePermission(Long id, UserDetail user) {
        List<RolePermission> list= rolePermissionRepository.findByRoleIdAndClient(id,user.getClient());
        return ReSult.success(list);
    }
}
