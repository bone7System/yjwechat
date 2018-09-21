package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDtoC {

    @ApiModelProperty("客户编号")
    private Long  kunnr;
    @ApiModelProperty("来源单号")
    private String yddh;
    @ApiModelProperty("源单类型")
    private String ydlx;
    @ApiModelProperty("订单类型")
    private Long type;
    @ApiModelProperty("交货方式")
    private String jhfs;
    @ApiModelProperty("运送方式")
    private String ysfs;
    @ApiModelProperty("交货方式")
    private String jsfs;
    @ApiModelProperty("订货方式")
    private String dhfs;
    @ApiModelProperty(name = "客户订单号")
    private String kunnrOrder;
    @ApiModelProperty("备注")
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("订单时间")
    private Date orderTime;
    @ApiModelProperty("应收金额")
    private BigDecimal ysje;

    @ApiModelProperty("实收金额")
    private BigDecimal shje;

    @ApiModelProperty( "折扣")
    private BigDecimal zk;

    @ApiModelProperty("优惠卷")
    private BigDecimal yhj;

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

    public Long getKunnr() {
        return kunnr;
    }

    public void setKunnr(Long kunnr) {
        this.kunnr = kunnr;
    }

    public String getYddh() {
        return yddh;
    }

    public void setYddh(String yddh) {
        this.yddh = yddh;
    }

    public String getYdlx() {
        return ydlx;
    }

    public void setYdlx(String ydlx) {
        this.ydlx = ydlx;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getJhfs() {
        return jhfs;
    }

    public void setJhfs(String jhfs) {
        this.jhfs = jhfs;
    }

    public String getYsfs() {
        return ysfs;
    }

    public void setYsfs(String ysfs) {
        this.ysfs = ysfs;
    }

    public String getJsfs() {
        return jsfs;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public String getDhfs() {
        return dhfs;
    }

    public void setDhfs(String dhfs) {
        this.dhfs = dhfs;
    }

    public String getKunnrOrder() {
        return kunnrOrder;
    }

    public void setKunnrOrder(String kunnrOrder) {
        this.kunnrOrder = kunnrOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }


}
