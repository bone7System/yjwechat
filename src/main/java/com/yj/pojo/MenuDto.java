package com.yj.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

public class MenuDto{
        @ApiModelProperty("id ")
        private Long id;
        @ApiModelProperty("标题 ")
        private String title;
        @ApiModelProperty("访问地址 ")
        private String url;
        @ApiModelProperty("图标 ")
        private String icon;
        @ApiModelProperty("父节点 ")
        private Long parentId;
        @ApiModelProperty("枝叶（0 枝，1叶）")
        private Long isDirectory;
        @ApiModelProperty("排序码 ")
        private Long sort;
        @ApiModelProperty("状态 ")
        private Long status;

        @ApiModelProperty("id路径 ")
        private String path;

        @Column(name = "createtime")
        @Temporal(TemporalType.TIMESTAMP)
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
        private Date createTime;
}
