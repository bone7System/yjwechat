package com.yj.pojo.price;

import io.swagger.annotations.ApiModelProperty;

public class PriceTypeDtoC {
    @ApiModelProperty("名称")
    private String mc;

    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }
}
