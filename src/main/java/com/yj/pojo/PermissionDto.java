package com.yj.pojo;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class PermissionDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "menuId")
    private String menuId;

    // read, update, admin...
    @Column(name = "permission")
    private String permission;

    @Column(name = "description")
    private String description;

    @Column(name = "prantid")
    private Long prantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrantId() {
        return prantId;
    }

    public void setPrantId(Long prantId) {
        this.prantId = prantId;
    }
}
