package com.yj.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

public class OrderDetailDtoC {
    @ApiModelProperty("行项目")
    private Long rownum;
    @ApiModelProperty("商品编码")
    private String spbm;
    @ApiModelProperty("商品数量")
    private BigDecimal count;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("最晚交货日期")
    private Date zwsj;
    @ApiModelProperty("备注")
    private String remark;

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


    public Date getZwsj() {
        return zwsj;
    }

    public void setZwsj(Date zwsj) {
        this.zwsj = zwsj;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
