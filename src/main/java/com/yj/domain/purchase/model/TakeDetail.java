package com.yj.domain.purchase.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "erp_take_detail")
@Entity
public class TakeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long client;
    private Long rkdh;
    private Long rownum;
    private Long cgrownum;
    private Long cw;
    private String spbm;
    private BigDecimal count;
    private String remark;

    public Long getCgrownum() {
        return cgrownum;
    }

    public void setCgrownum(Long cgrownum) {
        this.cgrownum = cgrownum;
    }

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

    public Long getRkdh() {
        return rkdh;
    }

    public void setRkdh(Long rkdh) {
        this.rkdh = rkdh;
    }

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
