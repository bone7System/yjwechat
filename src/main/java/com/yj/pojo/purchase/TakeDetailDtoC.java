package com.yj.pojo.purchase;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

public class TakeDetailDtoC {

//    @ApiModelProperty("客户端")
//    private Long client;
//    @ApiModelProperty("入库单号")
//    private Long rkdh;
    @ApiModelProperty("行项目")
    private Long rownum;
    @ApiModelProperty("采购行项目")
    private Long cgrownum;
    @ApiModelProperty("入库仓位")
    private Long cw;
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("商品数量")
    private BigDecimal count;
    @ApiModelProperty("备注")
    private String remark;

    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }

//    public Long getClient() {
//        return client;
//    }
//
//    public void setClient(Long client) {
//        this.client = client;
//    }
//
//    public Long getRkdh() {
//        return rkdh;
//    }
//
//    public void setRkdh(Long rkdh) {
//        this.rkdh = rkdh;
//    }

    public Long getRownum() {
        return rownum;
    }

    public void setRownum(Long rownum) {
        this.rownum = rownum;
    }

    public Long getCgrownum() {
        return cgrownum;
    }

    public void setCgrownum(Long cgrownum) {
        this.cgrownum = cgrownum;
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
