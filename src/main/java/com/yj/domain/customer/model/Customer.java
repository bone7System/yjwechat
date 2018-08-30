package com.yj.domain.customer.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "erp_kan1")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;
    @Column(name = "type")
    private String type;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;
    @Column(name = "khfzr")
    private String khfzr;
    @Column(name = "khphone")
    private String khphone;
    @Column(name = "ywfzr")
    private String ywfzr;
    @Column(name = "ywphone")
    private String ywphone;
    @Column(name = "fhaddr")
    private String fhaddr;

    @Column(name = "fhfs")
    private String fhfs;

    @Column(name = "goodsman")
    private String goodsman;

    @Column(name = "goodsphone")
    private String goodsphone;

    @Column(name = "yb")
    private String yb;

    @Column(name = "remark")
    private String remark;

    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @Column(name = "createuserid")
    private Long createUserid;

    public Long getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Long createUserid) {
        this.createUserid = createUserid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKhfzr() {
        return khfzr;
    }

    public void setKhfzr(String khfzr) {
        this.khfzr = khfzr;
    }

    public String getKhphone() {
        return khphone;
    }

    public void setKhphone(String khphone) {
        this.khphone = khphone;
    }

    public String getYwfzr() {
        return ywfzr;
    }

    public void setYwfzr(String ywfzr) {
        this.ywfzr = ywfzr;
    }

    public String getYwphone() {
        return ywphone;
    }

    public void setYwphone(String ywphone) {
        this.ywphone = ywphone;
    }

    public String getFhaddr() {
        return fhaddr;
    }

    public void setFhaddr(String fhaddr) {
        this.fhaddr = fhaddr;
    }

    public String getFhfs() {
        return fhfs;
    }

    public void setFhfs(String fhfs) {
        this.fhfs = fhfs;
    }

    public String getGoodsman() {
        return goodsman;
    }

    public void setGoodsman(String goodsman) {
        this.goodsman = goodsman;
    }

    public String getGoodsphone() {
        return goodsphone;
    }

    public void setGoodsphone(String goodsphone) {
        this.goodsphone = goodsphone;
    }

    public String getYb() {
        return yb;
    }

    public void setYb(String yb) {
        this.yb = yb;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
