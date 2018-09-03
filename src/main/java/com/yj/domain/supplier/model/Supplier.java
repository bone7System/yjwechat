package com.yj.domain.supplier.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "erp_lfa1")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;

    @Column(name = "type")
    private String type;

    @Column(name = "name")
    private String name;

    @Column(name = "city")
    private String city;

    @Column(name = "jlfw")
    private String jlfw;

    @Column(name = "product")
    private String product;

    @Column(name = "address")
    private String address;

    @Column(name = "zcbh")
    private String zcbh;

    @Column(name = "gsxz")
    private String gsxz;

    @Column(name = "jgdm")
    private String jgdm;

    @Column(name = "dlfr")
    private String dlfr;

    @Column(name = "yyzz")
    private String yyzz;

    @Column(name = "remark")
    private String remark;

    @Column(name = "flag")
    private Long flag;

    @Column(name = "createtime")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss.SSS")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private Date createTime;

    @Column(name = "createuser")
    private Long createUserid;

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJlfw() {
        return jlfw;
    }

    public void setJlfw(String jlfw) {
        this.jlfw = jlfw;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZcbh() {
        return zcbh;
    }

    public void setZcbh(String zcbh) {
        this.zcbh = zcbh;
    }

    public String getGsxz() {
        return gsxz;
    }

    public void setGsxz(String gsxz) {
        this.gsxz = gsxz;
    }

    public String getJgdm() {
        return jgdm;
    }

    public void setJgdm(String jgdm) {
        this.jgdm = jgdm;
    }

    public String getDlfr() {
        return dlfr;
    }

    public void setDlfr(String dlfr) {
        this.dlfr = dlfr;
    }

    public String getYyzz() {
        return yyzz;
    }

    public void setYyzz(String yyzz) {
        this.yyzz = yyzz;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
