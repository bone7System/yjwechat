package com.yj.security;

import com.yj.domain.user.model.Permission;
import com.yj.domain.user.model.Role;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyGrantedAuthority implements GrantedAuthority {
    @Override
    public String getAuthority() {
        return null;
    }
    //角色列表
    private Set<String>  roles = new HashSet<>();
    //权限列表
    private Set<String> permissions = new HashSet<>();

    public Set<String> getRoles(){
        return this.roles;
    }
    public void addRole(String role){
        this.roles.add(role);
    }
    public Set<String> getPermissions(){
        return this.permissions;
    }
    public void addPermission(String permission){
        this.permissions.add(permission);
    }

}
