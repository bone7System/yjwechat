package com.yj.pojo.supplier;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class SupplierDtoC {

        @ApiModelProperty("类别")
        private String type;

        @ApiModelProperty("供应商名称")
        private String name;

        @ApiModelProperty("城市")
        private String city;

        @ApiModelProperty("供应商名称")
        private String jlfw;

        @ApiModelProperty("经营范围")
        private String product;

        @ApiModelProperty("地址")
        private String address;

        @ApiModelProperty("注册编号")
        private String zcbh;

        @ApiModelProperty("公司性质")
        private String gsxz;

        @ApiModelProperty("组织结构代码")
        private String jgdm;

        @ApiModelProperty("是否是独立法人")
        private String dlfr;

        @ApiModelProperty("营业执照注册号")
        private String yyzz;

        @ApiModelProperty("备注")
        private String remark;


        public String getType() {
                return type;
        }

        public void setType(String type) {
                this.type = type;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getJlfw() {
                return jlfw;
        }

        public void setJlfw(String jlfw) {
                this.jlfw = jlfw;
        }

        public String getProduct() {
                return product;
        }

        public void setProduct(String product) {
                this.product = product;
        }

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public String getZcbh() {
                return zcbh;
        }

        public void setZcbh(String zcbh) {
                this.zcbh = zcbh;
        }

        public String getGsxz() {
                return gsxz;
        }

        public void setGsxz(String gsxz) {
                this.gsxz = gsxz;
        }

        public String getJgdm() {
                return jgdm;
        }

        public void setJgdm(String jgdm) {
                this.jgdm = jgdm;
        }

        public String getDlfr() {
                return dlfr;
        }

        public void setDlfr(String dlfr) {
                this.dlfr = dlfr;
        }

        public String getYyzz() {
                return yyzz;
        }

        public void setYyzz(String yyzz) {
                this.yyzz = yyzz;
        }

        public String getRemark() {
                return remark;
        }

        public void setRemark(String remark) {
                this.remark = remark;
        }
}
