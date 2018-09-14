package com.yj.pojo.purchase.vo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
@Entity
public class PurchaseVo {

    @ApiModelProperty("id")
    @Id
    private Long id;
    @ApiModelProperty("签单日期")
    private Date qdrq;
    @ApiModelProperty("来源单据")
    private String ydlx;
    @ApiModelProperty("交货方式")
    private String jhfs;
    @ApiModelProperty("运送方式")
    private String ysfs;
    @ApiModelProperty("结算方式")
    private String jsfs;
    @ApiModelProperty("支付方式")
    private String zffs;
    @ApiModelProperty("供应商")
    private Long  lifnr;
    @ApiModelProperty("供应商订单号")
    private String lifnrOrder;
    @ApiModelProperty("备注")
    private String remark;
    @ApiModelProperty("状态")
    private String status;
    @ApiModelProperty("总价格")
    private BigDecimal kbetr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getQdrq() {
        return qdrq;
    }

    public void setQdrq(Date qdrq) {
        this.qdrq = qdrq;
    }

    public String getYdlx() {
        return ydlx;
    }

    public void setYdlx(String ydlx) {
        this.ydlx = ydlx;
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

    public Long getLifnr() {
        return lifnr;
    }

    public void setLifnr(Long lifnr) {
        this.lifnr = lifnr;
    }

    public String getLifnrOrder() {
        return lifnrOrder;
    }

    public void setLifnrOrder(String lifnrOrder) {
        this.lifnrOrder = lifnrOrder;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getKbetr() {
        return kbetr;
    }

    public void setKbetr(BigDecimal kbetr) {
        this.kbetr = kbetr;
    }
}
