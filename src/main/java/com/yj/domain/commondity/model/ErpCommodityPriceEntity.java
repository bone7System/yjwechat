package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity_price", schema = "yj", catalog = "")
public class ErpCommodityPriceEntity {
    private Integer id;
    private Double bzjg;
    private Integer spxqid;
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
    @Column(name = "bzjg")
    public Double getBzjg() {
        return bzjg;
    }

    public void setBzjg(Double bzjg) {
        this.bzjg = bzjg;
    }

    @Basic
    @Column(name = "spxqid")
    public Integer getSpxqid() {
        return spxqid;
    }

    public void setSpxqid(Integer spxqid) {
        this.spxqid = spxqid;
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
        ErpCommodityPriceEntity that = (ErpCommodityPriceEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(bzjg, that.bzjg) &&
                Objects.equals(spxqid, that.spxqid) &&
                Objects.equals(delFlag, that.delFlag);
    }



    @Override
    public int hashCode() {

        return Objects.hash(id, bzjg , spxqid , delFlag);
    }
}
