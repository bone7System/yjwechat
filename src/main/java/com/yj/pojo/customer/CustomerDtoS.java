package com.yj.pojo.customer;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class CustomerDtoS {


    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("地址")
    private String address;

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
}
