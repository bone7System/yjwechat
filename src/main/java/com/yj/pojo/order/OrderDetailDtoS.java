package com.yj.pojo.order;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailDtoS {

    @ApiModelProperty("订单编号")
    private Long id;
    @ApiModelProperty("客户编号")
    private Long kunnr;
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("订单开始时间")
    private Date beginTime;
    @ApiModelProperty("订单结束时间")
    private Date endTime;
    @ApiModelProperty("销售类别")
    private Long type;
    @ApiModelProperty("实收金额")
    private BigDecimal beginShje;
    @ApiModelProperty("实收金额")
    private BigDecimal endShje;
    @ApiModelProperty("商品名称")
    private String spmc;
    @ApiModelProperty("商品类型")
    private String splxdm;
    @ApiModelProperty("品牌")
    private String sppp;
    @ApiModelProperty("规格")
    private String gg;
    @ApiModelProperty("等级")
    private String dj;

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSplxdm() {
        return splxdm;
    }

    public void setSplxdm(String splxdm) {
        this.splxdm = splxdm;
    }

    public String getSppp() {
        return sppp;
    }

    public void setSppp(String sppp) {
        this.sppp = sppp;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKunnr() {
        return kunnr;
    }

    public void setKunnr(Long kunnr) {
        this.kunnr = kunnr;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
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

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
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
}
