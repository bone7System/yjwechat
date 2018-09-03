package com.yj.pojo.customer;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class CustomerDtoC {

    @NotNull
    @ApiModelProperty("类型")
    private String type;
    @NotNull
    @ApiModelProperty("名称")
    private String name;
    @NotNull
    @ApiModelProperty("地址")
    private String address;
    @ApiModelProperty("客户负责人")
    private String khfzr;
    @ApiModelProperty("客户负责人电话")
    private String khphone;
    @ApiModelProperty("业务负责人")
    private String ywfzr;
    @ApiModelProperty("业务负责人电话")
    private String ywphone;
    @ApiModelProperty("发货地址")
    private String fhaddr;
    @ApiModelProperty("发货方式")
    private String fhfs;
    @ApiModelProperty("接收人")
    private String goodsman;
    @ApiModelProperty("接收人电话")
    private String goodsphone;
    @ApiModelProperty("邮编")
    private String yb;
    @ApiModelProperty("备注")
    private String remark;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKhfzr() {
        return khfzr;
    }

    public void setKhfzr(String khfzr) {
        this.khfzr = khfzr;
    }

    public String getKhphone() {
        return khphone;
    }

    public void setKhphone(String khphone) {
        this.khphone = khphone;
    }

    public String getYwfzr() {
        return ywfzr;
    }

    public void setYwfzr(String ywfzr) {
        this.ywfzr = ywfzr;
    }

    public String getYwphone() {
        return ywphone;
    }

    public void setYwphone(String ywphone) {
        this.ywphone = ywphone;
    }

    public String getFhaddr() {
        return fhaddr;
    }

    public void setFhaddr(String fhaddr) {
        this.fhaddr = fhaddr;
    }

    public String getFhfs() {
        return fhfs;
    }

    public void setFhfs(String fhfs) {
        this.fhfs = fhfs;
    }

    public String getGoodsman() {
        return goodsman;
    }

    public void setGoodsman(String goodsman) {
        this.goodsman = goodsman;
    }

    public String getGoodsphone() {
        return goodsphone;
    }

    public void setGoodsphone(String goodsphone) {
        this.goodsphone = goodsphone;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
