package com.yj.pojo.purchase;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

public class TakeDtoC {
    @ApiModelProperty("编号")
    private Long type=1L;
//    @ApiModelProperty("客户端")
//    private Long client;
    @ApiModelProperty("源单单号")
    private Long yddh;
    @ApiModelProperty("源单类型")
    private String ydlx;
    @ApiModelProperty("点收日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dsrq;
    @ApiModelProperty("到货日期")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date dhrq;
    @ApiModelProperty("签收人")
    private String qsr;
    @ApiModelProperty("收货地址")
    private String shdz;
    @ApiModelProperty("状态")
    private Long status;
    @ApiModelProperty("备注")
    private String remark;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public Long getClient() {
//        return client;
//    }
//
//    public void setClient(Long client) {
//        this.client = client;
//    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getYddh() {
        return yddh;
    }

    public void setYddh(Long yddh) {
        this.yddh = yddh;
    }

    public String getYdlx() {
        return ydlx;
    }

    public void setYdlx(String ydlx) {
        this.ydlx = ydlx;
    }

    public Date getDsrq() {
        return dsrq;
    }

    public void setDsrq(Date dsrq) {
        this.dsrq = dsrq;
    }

    public Date getDhrq() {
        return dhrq;
    }

    public void setDhrq(Date dhrq) {
        this.dhrq = dhrq;
    }

    public String getQsr() {
        return qsr;
    }

    public void setQsr(String qsr) {
        this.qsr = qsr;
    }

    public String getShdz() {
        return shdz;
    }

    public void setShdz(String shdz) {
        this.shdz = shdz;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
