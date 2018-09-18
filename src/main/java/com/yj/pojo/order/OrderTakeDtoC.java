package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;


public class OrderTakeDtoC {

    @ApiModelProperty("单号来源")
    private Long lydh;
    @ApiModelProperty("单号类型")
    private String lylx;
    @ApiModelProperty("交货方式")
    private String jhfs;
    @ApiModelProperty("运送方式")
    private String ysfs;
    @ApiModelProperty("交货方式")
    private String jsfs;
    @ApiModelProperty("发货人")
    private String fhr;
    @ApiModelProperty("预计发货时间")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date yjfhsj;
    @ApiModelProperty("收货人")
    private Date shr;
    @ApiModelProperty("收货地址")
    private String address;
    @ApiModelProperty("收货人电话")
    private String phone;
    @ApiModelProperty("是否冲销")
    private Long cxdh;

    public Long getLydh() {
        return lydh;
    }

    public void setLydh(Long lydh) {
        this.lydh = lydh;
    }

    public String getLylx() {
        return lylx;
    }

    public void setLylx(String lylx) {
        this.lylx = lylx;
    }

    public String getJhfs() {
        return jhfs;
    }

    public void setJhfs(String jhfs) {
        this.jhfs = jhfs;
    }

    public String getYsfs() {
        return ysfs;
    }

    public void setYsfs(String ysfs) {
        this.ysfs = ysfs;
    }

    public String getJsfs() {
        return jsfs;
    }

    public void setJsfs(String jsfs) {
        this.jsfs = jsfs;
    }

    public String getFhr() {
        return fhr;
    }

    public void setFhr(String fhr) {
        this.fhr = fhr;
    }

    public Date getYjfhsj() {
        return yjfhsj;
    }

    public void setYjfhsj(Date yjfhsj) {
        this.yjfhsj = yjfhsj;
    }

    public Date getShr() {
        return shr;
    }

    public void setShr(Date shr) {
        this.shr = shr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCxdh() {
        return cxdh;
    }

    public void setCxdh(Long cxdh) {
        this.cxdh = cxdh;
    }
}
