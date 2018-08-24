package com.yj.domain.dmk.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "YJ_DMK_CL", schema = "yj", catalog = "")
public class YjDmkClEntity {
    private int pkid;
    private String dm;
    private String mc;
    private String jc;
    private int pxh;
    private String bq;
    private String zt;
    private String dmbz;
    private Integer fid;

    @Id
    @Column(name = "PKID")
    public int getPkid() {
        return pkid;
    }

    public void setPkid(int pkid) {
        this.pkid = pkid;
    }

    @Basic
    @Column(name = "DM")
    public String getDm() {
        return dm;
    }

    public void setDm(String dm) {
        this.dm = dm;
    }

    @Basic
    @Column(name = "MC")
    public String getMc() {
        return mc;
    }

    public void setMc(String mc) {
        this.mc = mc;
    }

    @Basic
    @Column(name = "JC")
    public String getJc() {
        return jc;
    }

    public void setJc(String jc) {
        this.jc = jc;
    }

    @Basic
    @Column(name = "PXH")
    public int getPxh() {
        return pxh;
    }

    public void setPxh(int pxh) {
        this.pxh = pxh;
    }

    @Basic
    @Column(name = "BQ")
    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    @Basic
    @Column(name = "ZT")
    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        YjDmkClEntity that = (YjDmkClEntity) o;
        return pkid == that.pkid &&
                pxh == that.pxh &&
                Objects.equals(dm, that.dm) &&
                Objects.equals(mc, that.mc) &&
                Objects.equals(jc, that.jc) &&
                Objects.equals(bq, that.bq) &&
                Objects.equals(zt, that.zt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(pkid, dm, mc, jc, pxh, bq, zt);
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
    @Column(name = "FID")
    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }
}
