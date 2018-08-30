package com.yj.pojo.system.user;

import io.swagger.annotations.ApiModelProperty;

public class UserSearchDto {
    @ApiModelProperty("客户端")
    private Long client;
    @ApiModelProperty("姓名")
    private String name;
    @ApiModelProperty("手机")
    private String phone;
    @ApiModelProperty("部门id")
    private Long deptId;

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
