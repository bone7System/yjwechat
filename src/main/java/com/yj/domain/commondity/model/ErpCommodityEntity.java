package com.yj.domain.commondity.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "erp_commodity", schema = "yj", catalog = "")
public class ErpCommodityEntity {
    private Long id;
    private Long client;
    private String spbm;//商品编号
    private String spmc;//名称
    private String spms;//描述
    private String splxdm;//商品类型
    private String sppp;//品牌
    private String gg;//规格
    private String dj;//等级
    private String dwdm;//单位
    private BigDecimal kbetr;//移动平均价
    private String pic;//正面图
    private String pic2;//侧面
    private String pic3;
    private String pic4;
    private String pic5;
    private Integer delFlag;

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic2() {
        return pic2;
    }

    public void setPic2(String pic2) {
        this.pic2 = pic2;
    }

    public String getPic3() {
        return pic3;
    }

    public void setPic3(String pic3) {
        this.pic3 = pic3;
    }

    public String getPic4() {
        return pic4;
    }

    public void setPic4(String pic4) {
        this.pic4 = pic4;
    }

    public String getPic5() {
        return pic5;
    }

    public void setPic5(String pic5) {
        this.pic5 = pic5;
    }

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

    public BigDecimal getKbetr() {
        return kbetr;
    }

    public void setKbetr(BigDecimal kbetr) {
        this.kbetr = kbetr;
    }

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
