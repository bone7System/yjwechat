package com.yj.pojo.price;

import io.swagger.annotations.ApiModelProperty;

public class TjDtoU {
    @ApiModelProperty("主键")
    private Long id;
    @ApiModelProperty("描述")
    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
