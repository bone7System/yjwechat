package com.yj.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;


public class OrderTakeDetailDtoC {
    @ApiModelProperty("类型")
    private Long type=1L;//默认 出库
    @ApiModelProperty("行项目")
    private Long rowNum;
    @ApiModelProperty("订单行项目")
    private Long orderRowNum;
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("数量")
    private BigDecimal count;
    @ApiModelProperty("库位")
    private Long cw;
    @ApiModelProperty("备注")
    private String remark;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public Long getOrderRowNum() {
        return orderRowNum;
    }

    public void setOrderRowNum(Long orderRowNum) {
        this.orderRowNum = orderRowNum;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
