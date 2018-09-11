package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity", schema = "yj", catalog = "")
public class ErpCommodityEntity {
    private Integer id;
    private Long client;
    private String spbm;//商品编号
    private String spmc;//名称
    private String spms;//描述
    private String splxdm;//商品类型
    private String sppp;//品牌
    private String gg;//规格
    private String dj;//等级
    private String dwdm;//单位
    private float kbetr;//移动平均价
    private Integer delFlag;


    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
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

    public float getKbetr() {
        return kbetr;
    }

    public void setKbetr(float kbetr) {
        this.kbetr = kbetr;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "spbm")
    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    @Basic
    @Column(name = "spmc")
    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    @Basic
    @Column(name = "spms")
    public String getSpms() {
        return spms;
    }

    public void setSpms(String spms) {
        this.spms = spms;
    }

    @Basic
    @Column(name = "splxdm")
    public String getSplxdm() {
        return splxdm;
    }

    public void setSplxdm(String splxdm) {
        this.splxdm = splxdm;
    }


    @Basic
    @Column(name = "sppp")
    public String getSppp() {
        return sppp;
    }

    public void setSppp(String sppp) {
        this.sppp = sppp;
    }

    @Basic
    @Column(name = "del_flag", nullable = false)
    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErpCommodityEntity that = (ErpCommodityEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(spbm, that.spbm) &&
                Objects.equals(spmc, that.spmc) &&
                Objects.equals(spms, that.spms) &&
                Objects.equals(splxdm, that.splxdm) &&
                Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id);
        result = 31 * result + Objects.hashCode(spbm);
        result = 31 * result + Objects.hashCode(spmc);
        result = 31 * result + Objects.hashCode(spms);
        result = 31 * result + Objects.hashCode(splxdm);
        result = 31 * result + Objects.hashCode(sppp);
        result = 31 * result + Objects.hashCode(delFlag);
        return result;
    }
}
