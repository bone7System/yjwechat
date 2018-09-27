package com.yj.pojo.report;

import io.swagger.annotations.ApiModelProperty;

public class KcDto {
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("商品名称")
    private String sbmc;
    @ApiModelProperty("仓位")
    private Long cw;
    @ApiModelProperty("品牌")
    private String sppp;
    @ApiModelProperty("规格")
    private String gg;
    @ApiModelProperty("等级")
    private String dj;
//    @ApiModelProperty("库存量（默认库存大于0.01）")
//    private String count;

    public String getSpbm() {
        return spbm;
    }

    public void setSpbm(String spbm) {
        this.spbm = spbm;
    }

    public String getSbmc() {
        return sbmc;
    }

    public void setSbmc(String sbmc) {
        this.sbmc = sbmc;
    }

    public Long getCw() {
        return cw;
    }

    public void setCw(Long cw) {
        this.cw = cw;
    }

    public String getSppp() {
        return sppp;
    }

    public void setSppp(String sppp) {
        this.sppp = sppp;
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
}
