package com.yj.domain.user.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class RoleInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "status")
    private Long status;

    @Column(name = "description")
    private String description;

    @Column(name = "issystem")
    private Long issystem;

    @OneToMany(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(
            name="role_permission",
            joinColumns=@JoinColumn(name="role_id", referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="permission_id", referencedColumnName="id", unique=true))
    private List<Permission> permissionList;
    @Column(name = "org_id")
    private Long orgid;
    @Column(name = "orgname")
    private String orgname;

    public Long getOrgid() {
        return orgid;
    }

    public void setOrgid(Long orgid) {
        this.orgid = orgid;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public Long getId() {
        return id;
    }

    public Long getIssystem() {
        return issystem;
    }

    public void setIssystem(Long issystem) {
        this.issystem = issystem;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
