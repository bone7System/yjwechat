package com.yj.domain.order.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Table(name = "erp_order_take_detail")
@Entity
public class OrderTakeDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "client")
    private Long client;
    private Long tkid;//交货编号
    private Long type=1L;//默认 出库
    @Column(name = "rownum")
    private Long rowNum;
    @Column(name = "orderrownum")
    private Long orderRowNum;
    @Column(name = "spbm")
    private String spbm;

    private BigDecimal count;

    private Long cw;

    private String remark;

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

    public Long getTkid() {
        return tkid;
    }

    public void setTkid(Long tkid) {
        this.tkid = tkid;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getRowNum() {
        return rowNum;
    }

    public void setRowNum(Long rowNum) {
        this.rowNum = rowNum;
    }

    public Long getOrderRowNum() {
        return orderRowNum;
    }

    public void setOrderRowNum(Long orderRowNum) {
        this.orderRowNum = orderRowNum;
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

    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
