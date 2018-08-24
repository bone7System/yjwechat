package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity_detail", schema = "yj", catalog = "")
public class ErpCommodityDetailEntity {
    private Integer id;
    private String gg;
    private String dj;
    private String dwdm;
    private Integer spid;
    private Integer delFlag;

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "gg")
    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    @Basic
    @Column(name = "dj")
    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    @Basic
    @Column(name = "spid")
    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer dwdm) {
        this.spid = spid;
    }

    @Basic
    @Column(name = "dwdm")
    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
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
        ErpCommodityDetailEntity that = (ErpCommodityDetailEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(gg, that.gg) &&
                Objects.equals(dj, that.dj) &&
                Objects.equals(dwdm, that.dwdm) &&
                Objects.equals(spid, that.spid) &&
                Objects.equals(delFlag, that.delFlag);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gg, dj, dwdm , spid , delFlag);
    }
}
