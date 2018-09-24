package com.yj.pojo.depot;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class DepotDtoC {
    @ApiModelProperty("名称")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
