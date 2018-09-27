package com.yj.pojo.system.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

public class UserDetailDto {
    @ApiModelProperty("用户编号")
    private Long id;

    @ApiModelProperty("用户部门")
    private Long deptId;

    @ApiModelProperty("用户类型")
    private String type;

    @ApiModelProperty("用户手机")
    private String  phone;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("性别")
    private String sex;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("证件号")
    private String  certificate;

    @ApiModelProperty("兴趣爱好")
    private String interest;

    @ApiModelProperty("角色")
    List<Long> roleIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }
}
