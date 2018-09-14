package com.yj.domain.purchase.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PurchaseResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long client;
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    private Date qdrq;
    @Column
    private String ydlx;
    @Column
    private String jhfs;
    @Transient
    private String jhfsName;
    @Column
    private String ysfs;
    @Transient
    private String ysfsName;
    @Column
    private String jsfs;
    @Transient
    private String jsfsName;
    @Column
    private String zffs;
    @Column
    private Long  lifnr;
    private String  lifnrName;
    private String lifnrOrder;
    @Column
    private String remark;
    @Column
    private String status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private Long createUser;
    private String userName;
    private BigDecimal kbetr;
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

    public String getJhfsName() {
        return jhfsName;
    }

    public void setJhfsName(String jhfsName) {
        this.jhfsName = jhfsName;
    }

    public String getYsfs() {
        return ysfs;
    }

    public void setYsfs(String ysfs) {
        this.ysfs = ysfs;
    }

    public String getYsfsName() {
        return ysfsName;
    }

    public void setYsfsName(String ysfsName) {
        this.ysfsName = ysfsName;
    }

    public String getJsfs() {
        return jsfs;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public String getJsfsName() {
        return jsfsName;
    }

    public void setJsfsName(String jsfsName) {
        this.jsfsName = jsfsName;
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

    public String getLifnrName() {
        return lifnrName;
    }

    public void setLifnrName(String lifnrName) {
        this.lifnrName = lifnrName;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getKbetr() {
        return kbetr;
    }

    public void setKbetr(BigDecimal kbetr) {
        this.kbetr = kbetr;
    }
}
