package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity", schema = "yj", catalog = "")
public class ErpCommodityEntity {
    private Integer id;
    private String spbm;
    private String spmc;
    private String spms;
    private String splxdm;
    private String sppp;
    private Integer mdid;
    private Integer delFlag;

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
    @Column(name = "mdid")
    public Integer getMdid() {
        return mdid;
    }

    public void setMdid(Integer mdid) {
        this.mdid = mdid;
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
                Objects.equals(sppp, that.sppp) &&
                Objects.equals(mdid, that.mdid) &&
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
        result = 31 * result + Objects.hashCode(mdid);
        result = 31 * result + Objects.hashCode(delFlag);
        return result;
    }
}
