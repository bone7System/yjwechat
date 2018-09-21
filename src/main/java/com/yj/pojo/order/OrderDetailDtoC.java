package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailDtoC {
    @ApiModelProperty("行项目")
    private Long type=1L;
    @ApiModelProperty("行项目")
    private Long rownum;
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("商品数量")
    private BigDecimal count;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("最晚交货日期")
    private Date zwsj;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("应收金额")
    private BigDecimal ysje;

    @ApiModelProperty("实收金额")
    private BigDecimal shje;

    @ApiModelProperty("折扣")
    private BigDecimal zk;

    @ApiModelProperty("优惠卷")
    private BigDecimal yhj;

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public BigDecimal getYsje() {
        return ysje;
    }

    public void setYsje(BigDecimal ysje) {
        this.ysje = ysje;
    }

    public BigDecimal getShje() {
        return shje;
    }

    public void setShje(BigDecimal shje) {
        this.shje = shje;
    }

    public BigDecimal getZk() {
        return zk;
    }

    public void setZk(BigDecimal zk) {
        this.zk = zk;
    }

    public BigDecimal getYhj() {
        return yhj;
    }

    public void setYhj(BigDecimal yhj) {
        this.yhj = yhj;
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

    public Date getZwsj() {
        return zwsj;
    }

    public void setZwsj(Date zwsj) {
        this.zwsj = zwsj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
