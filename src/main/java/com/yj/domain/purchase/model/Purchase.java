package com.yj.domain.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "erp_purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @Column
    private Date qdrq;
    @Column
    private String ydlx;
    @Column
    private String jhfs;
    @Column
    private String ysfs;
    @Column
    private String jsfs;
    @Column
    private String zffs;
    @Column
    private Long  lifnr;
    @Column
    private String lifnrOrder;
    @Column
    private String remark;
    @Column
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuser")
    private Long createUser;

//    @ApiModelProperty("id")
//    private Long id;
//    @ApiModelProperty("客户端")
//    private Long client;
//    @ApiModelProperty("签单日期")
//    private Date qdrq;
//    @ApiModelProperty("来源单据")
//    private String ydlx;
//    @ApiModelProperty("交货方式")
//    private String jhfs;
//    @ApiModelProperty("运送方式")
//    private String ysfs;
//    @ApiModelProperty("结算方式")
//    private String jsfs;
//    @ApiModelProperty("支付方式")
//    private String zffs;
//    @ApiModelProperty("供应商")
//    private Long  lifnr;
//    @ApiModelProperty("供应商订单号")
//    private String lifnrOrder;
//    @ApiModelProperty("备注")
//    private String remark;
//    @ApiModelProperty("状态")
//    private String status;
//    @ApiModelProperty("创建时间")
//    private Date createTime;
//    @ApiModelProperty("创建人")
//    private Long createUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}
