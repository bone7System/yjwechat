package com.yj.pojo.depot;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;

public class DepotDtoU {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("名称")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
