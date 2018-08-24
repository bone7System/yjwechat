package com.yj.domain.dmk.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "YJ_DMK_FL", schema = "yj", catalog = "")
public class YjDmkFlEntity {
    private int pkid;
    private String dmbz;
    private String dmmc;
    private String dmms;
    private String zt;
    private Integer pxh;
    private Integer fflid;

    @Id
    @Column(name = "PKID")
    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    @Basic
    @Column(name = "DMBZ")
    public String getDmbz() {
        return dmbz;
    }

    public void setDmbz(String dmbz) {
        this.dmbz = dmbz;
    }

    @Basic
    @Column(name = "DMMC")
    public String getDmmc() {
        return dmmc;
    }

    public void setDmmc(String dmmc) {
        this.dmmc = dmmc;
    }

    @Basic
    @Column(name = "DMMS")
    public String getDmms() {
        return dmms;
    }

    public void setDmms(String dmms) {
        this.dmms = dmms;
    }

    @Basic
    @Column(name = "ZT")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Basic
    @Column(name = "PXH")
    public Integer getPxh() {
        return pxh;
    }

    public void setPxh(Integer pxh) {
        this.pxh = pxh;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YjDmkFlEntity that = (YjDmkFlEntity) o;
        return pkid == that.pkid &&
                Objects.equals(dmbz, that.dmbz) &&
                Objects.equals(dmmc, that.dmmc) &&
                Objects.equals(dmms, that.dmms) &&
                Objects.equals(zt, that.zt) &&
                Objects.equals(pxh, that.pxh);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pkid, dmbz, dmmc, dmms, zt, pxh);
    }

    @Basic
    @Column(name = "FFLID")
    public Integer getFflid() {
        return fflid;
    }

    public void setFflid(Integer fflid) {
        this.fflid = fflid;
    }
}
