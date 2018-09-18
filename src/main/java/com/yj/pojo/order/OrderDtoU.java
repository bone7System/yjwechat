package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDtoU {
    @ApiModelProperty("id")
    private Long id;
    @ApiModelProperty("交货方式")
    private String jhfs;
    @ApiModelProperty("运送方式")
    private String ysfs;
    @ApiModelProperty("交货方式")
    private String jsfs;
    @ApiModelProperty("支付方式")
    private String zffs;
    @ApiModelProperty(name = "客户订单号")
    private String kunnrOrder;
    @ApiModelProperty("备注")
    private String remark;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("订单时间")
    private Date orderTime;
    @ApiModelProperty("订单总金额")
    private BigDecimal kbetr;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getZffs() {
        return zffs;
    }

    public void setZffs(String zffs) {
        this.zffs = zffs;
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

    public BigDecimal getKbetr() {
        return kbetr;
    }

    public void setKbetr(BigDecimal kbetr) {
        this.kbetr = kbetr;
    }
}
