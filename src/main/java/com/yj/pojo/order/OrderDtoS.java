package com.yj.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDtoS {
    @ApiModelProperty("订单编号")
    private Long id;
    @ApiModelProperty("客户编号")
    private Long kunnr;
    @ApiModelProperty("订单开始时间")
    private Date beginTime;
    @ApiModelProperty("订单结束时间")
    private Date endTime;
    @ApiModelProperty("交货状态")
    private Long status;
    @ApiModelProperty("实收总金额")
    private BigDecimal beginShje;
    @ApiModelProperty("实收总金额")
    private BigDecimal endShje;
    @ApiModelProperty("销售类别")
    private Long type;

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBeginShje() {
        return beginShje;
    }

    public void setBeginShje(BigDecimal beginShje) {
        this.beginShje = beginShje;
    }

    public BigDecimal getEndShje() {
        return endShje;
    }

    public void setEndShje(BigDecimal endShje) {
        this.endShje = endShje;
    }

    public Long getKunnr() {
        return kunnr;
    }

    public void setKunnr(Long kunnr) {
        this.kunnr = kunnr;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
