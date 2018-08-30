package com.yj.pojo.supplier;

import io.swagger.annotations.ApiModelProperty;

public class SupplierDtoS {

    @ApiModelProperty("供应商名称")
    private String name;

    @ApiModelProperty("城市")
    private String city;

    @ApiModelProperty("供应商名称")
    private String jlfw;

    @ApiModelProperty("地址")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJlfw() {
        return jlfw;
    }

    public void setJlfw(String jlfw) {
        this.jlfw = jlfw;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
