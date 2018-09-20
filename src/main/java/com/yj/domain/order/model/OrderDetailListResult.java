package com.yj.domain.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
public class OrderDetailListResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "kunnrname")
    private String kunnrName;

    @Column(name = "orderid")
    private Long orderId;
    @Column(name = "ordertype")
    private Long orderType;
    private Long rownum;
    private Long type;
    private String spbm;
    private String spmc;
    private String sppp;
    private String gg;
    private String dj;
    private String dwdm;
    private BigDecimal count;
    private BigDecimal count1;
    private BigDecimal count2;
    private Long status;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date zwsj;
    private String remark;

    @Column(name = "ysje")
    private BigDecimal ysje;

    @Column(name = "shje")
    private BigDecimal shje;

    @Column(name = "zk")
    private BigDecimal zk;

    @Column(name = "yhj")
    private BigDecimal yhj;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKunnrName() {
        return kunnrName;
    }

    public void setKunnrName(String kunnrName) {
        this.kunnrName = kunnrName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderType() {
        return orderType;
    }

    public void setOrderType(Long orderType) {
        this.orderType = orderType;
    }

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
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

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getCount1() {
        return count1;
    }

    public void setCount1(BigDecimal count1) {
        this.count1 = count1;
    }

    public BigDecimal getCount2() {
        return count2;
    }

    public void setCount2(BigDecimal count2) {
        this.count2 = count2;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
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
}

