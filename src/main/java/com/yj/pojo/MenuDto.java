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

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getUrl() {
                return url;
        }

        public void setUrl(String url) {
                this.url = url;
        }

        public String getIcon() {
                return icon;
        }

        public void setIcon(String icon) {
                this.icon = icon;
        }

        public Long getParentId() {
                return parentId;
        }

        public void setParentId(Long parentId) {
                this.parentId = parentId;
        }

        public Long getIsDirectory() {
                return isDirectory;
        }

        public void setIsDirectory(Long isDirectory) {
                this.isDirectory = isDirectory;
        }

        public Long getSort() {
                return sort;
        }

        public void setSort(Long sort) {
                this.sort = sort;
        }

        public Long getStatus() {
                return status;
        }

        public void setStatus(Long status) {
                this.status = status;
        }

        public String getPath() {
                return path;
        }

        public void setPath(String path) {
                this.path = path;
        }

        public Date getCreateTime() {
                return createTime;
        }

        public void setCreateTime(Date createTime) {
                this.createTime = createTime;
        }
}
