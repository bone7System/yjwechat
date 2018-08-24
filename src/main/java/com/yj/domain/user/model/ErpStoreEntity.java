package com.yj.domain.user.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "erp_store", schema = "yj", catalog = "")
public class ErpStoreEntity {
    private Integer id;
    private String kfddm;
    private String mdmc;
    private String mddz;
    private String mddzxq;
    private String sfszb;
    private Integer fmdid;
    private Date createDate;
    private Integer createMan;

    @Id
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "kfddm", nullable = true, length = 10)
    public String getKfddm() {
        return kfddm;
    }

    public void setKfddm(String kfddm) {
        this.kfddm = kfddm;
    }

    @Basic
    @Column(name = "mdmc", nullable = true, length = 10)
    public String getMdmc() {
        return mdmc;
    }

    public void setMdmc(String mdmc) {
        this.mdmc = mdmc;
    }

    @Basic
    @Column(name = "mddz", nullable = true, length = 10)
    public String getMddz() {
        return mddz;
    }

    public void setMddz(String mddz) {
        this.mddz = mddz;
    }

    @Basic
    @Column(name = "mddzxq", nullable = true, length = 10)
    public String getMddzxq() {
        return mddzxq;
    }

    public void setMddzxq(String mddzxq) {
        this.mddzxq = mddzxq;
    }

    @Basic
    @Column(name = "sfszb", nullable = true, length = 10)
    public String getSfszb() {
        return sfszb;
    }

    public void setSfszb(String sfszb) {
        this.sfszb = sfszb;
    }

    @Basic
    @Column(name = "fmdid", nullable = true, length = 10)
    public Integer getFmdid() {
        return fmdid;
    }

    public void setFmdid(Integer fmdid) {
        this.fmdid = fmdid;
    }

    @Basic
    @Column(name = "create_date", nullable = true, length = 10)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "create_man", nullable = true, length = 10)
    public Integer getCreateMan() {
        return createMan;
    }

    public void setCreateMan(Integer createMan) {
        this.createMan = createMan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ErpStoreEntity that = (ErpStoreEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(kfddm, that.kfddm) &&
                Objects.equals(mdmc, that.mdmc) &&
                Objects.equals(mddz, that.mddz) &&
                Objects.equals(mddzxq, that.mddzxq) &&
                Objects.equals(sfszb, that.sfszb) &&
                Objects.equals(fmdid, that.fmdid) &&
                Objects.equals(createDate, that.createDate) &&
                Objects.equals(createMan, that.createMan);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, kfddm, mdmc, mddz, mddzxq, sfszb, fmdid, createDate, createMan);
    }
}
