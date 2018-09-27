package com.yj.pojo.commidity;

import io.swagger.annotations.ApiModelProperty;

public class ErpCommodityU {

    @ApiModelProperty(value = "商品ID")
    private Integer spid;
    @ApiModelProperty(value = "商品名称")
    private String spmc;
    @ApiModelProperty(value = "商品描述")
    private String spms;
    @ApiModelProperty(value = "商品类型代码")
    private String splxdm;
    @ApiModelProperty(value = "商品品牌")
    private String sppp;
    @ApiModelProperty(value = "规格")
    private String gg;
    @ApiModelProperty(value = "等级")
    private String dj;
    @ApiModelProperty(value = "单位代码")
    private String dwdm;
    @ApiModelProperty(value = "正面图")
    private String pic;
    @ApiModelProperty(value = "侧面")
    private String pic2;
    @ApiModelProperty(value = "其他面图")
    private String pic3;
    @ApiModelProperty(value = "其他面图")
    private String pic4;
    @ApiModelProperty(value = "其他面图")
    private String pic5;

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

    public Integer getSpid() {
        return spid;
    }

    public void setSpid(Integer spid) {
        this.spid = spid;
    }

    public String getSpmc() {
        return spmc;
    }

    public void setSpmc(String spmc) {
        this.spmc = spmc;
    }

    public String getSpms() {
        return spms;
    }

    public void setSpms(String spms) {
        this.spms = spms;
    }

    public String getSplxdm() {
        return splxdm;
    }

    public void setSplxdm(String splxdm) {
        this.splxdm = splxdm;
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

    public String getDwdm() {
        return dwdm;
    }

    public void setDwdm(String dwdm) {
        this.dwdm = dwdm;
    }
}
