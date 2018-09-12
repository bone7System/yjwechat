package com.yj.pojo.supplier;

import io.swagger.annotations.ApiModelProperty;

public class SupplierLinkManDtoC {

    @ApiModelProperty("客户编号")
    private Long lfid;

    @ApiModelProperty("客户端")
    private Long client;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("qq")
    private String qq;

    @ApiModelProperty("地址")
    private String address;


    @ApiModelProperty("备注")
    private String remark;


    public Long getLfid() {
        return lfid;
    }

    public void setLfid(Long lfid) {
        this.lfid = lfid;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
