package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity_detail", schema = "yj", catalog = "")
public class ErpCommodityDetailEntity {
    private Long id;
    private Long client;
    private String gg;
    private String dj;
    private String dwdm;
    private Long spid;
    private Long cw;
    private BigDecimal count;//数量
    private Long delFlag;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
    public Long getSpid() {
        return spid;
    }

    public void setSpid(Long spid) {
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
    public Long getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Long delFlag) {
        this.delFlag = delFlag;
    }

    @Basic
    @Column(name = "cw")
    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }
    @Basic
    @Column(name = "client")
    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
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

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }
}
