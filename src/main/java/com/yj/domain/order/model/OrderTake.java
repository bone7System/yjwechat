package com.yj.domain.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Table(name = "erp_order_take")
@Entity
public class OrderTake {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;
    private Long lydh;
    private String lylx;
    private String jhfs;
    private String ysfs;
    private String jsfs;
    private String fhr;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yjfhsj;
    private Date shr;
    private String address;
    private String phone;
    private Long status;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "createtime")
    private Date createTime;
    @Column(name = "createuser")
    private Long createUser;
    private Long cxdh;

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

    public Long getLydh() {
        return lydh;
    }

    public void setLydh(Long lydh) {
        this.lydh = lydh;
    }

    public String getLylx() {
        return lylx;
    }

    public void setLylx(String lylx) {
        this.lylx = lylx;
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

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public Date getYjfhsj() {
        return yjfhsj;
    }

    public void setYjfhsj(Date yjfhsj) {
        this.yjfhsj = yjfhsj;
    }

    public Date getShr() {
        return shr;
    }

    public void setShr(Date shr) {
        this.shr = shr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    public Long getCxdh() {
        return cxdh;
    }

    public void setCxdh(Long cxdh) {
        this.cxdh = cxdh;
    }
}
