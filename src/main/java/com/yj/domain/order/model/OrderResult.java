package com.yj.domain.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class OrderResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;
    @Column
    private String yddh;
    @Column
    private String ydlx;
    @Column
    private Long type;
    @Column
    private String jhfs;
    @Column(name = "jhfsname")
    private String jhfsName;
    @Column
    private String ysfs;
    @Column(name = "ysfsname")
    private String ysfsName;
    @Column
    private String jsfs;
    @Column(name = "jsfsname")
    private String jsfsName;
    @Column
    private String dhfs;
    @Column(name = "dhfsname")
    private String dhfsName;
    @Column
    private Long  kunnr;
    @Column(name = "kunnrname")
    private String kunnrName;

    @Column(name = "kunnrorder")
    private String kunnrOrder;
    @Column
    private String remark;
    @Column
    private Long status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createtime")
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "ordertime")
    private Date orderTime;
    @Column(name = "createuser")
    private Long createUser;
    @Column(name = "username")
    private String userName;

    @Column(name = "ysje")
    private BigDecimal ysje;

    @Column(name = "shje")
    private BigDecimal shje;

    @Column(name = "zk")
    private BigDecimal zk;

    @Column(name = "yhj")
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

    public String getDhfs() {
        return dhfs;
    }

    public void setDhfs(String dhfs) {
        this.dhfs = dhfs;
    }

    public String getDhfsName() {
        return dhfsName;
    }

    public void setDhfsName(String dhfsName) {
        this.dhfsName = dhfsName;
    }

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


    public Long getKunnr() {
        return kunnr;
    }

    public void setKunnr(Long kunnr) {
        this.kunnr = kunnr;
    }

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
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

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
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


}
