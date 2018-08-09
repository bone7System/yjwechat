package com.yj.domain.user.model;

import java.util.List;

public class RolePermissionDto {
    private List<Long> permissions;
    private Long roleId;

    public List<Long> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Long> permissions) {
        this.permissions = permissions;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
