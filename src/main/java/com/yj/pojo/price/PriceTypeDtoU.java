package com.yj.pojo.price;

import io.swagger.annotations.ApiModelProperty;

public class PriceTypeDtoU {
    @ApiModelProperty("编号")
    private Long id;
    @ApiModelProperty("名称")
    private String mc;

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
