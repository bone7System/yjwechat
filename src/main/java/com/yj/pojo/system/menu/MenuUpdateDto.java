package com.yj.pojo.system.menu;

import io.swagger.annotations.ApiModelProperty;

public class MenuUpdateDto {
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

    @ApiModelProperty("是否是系统菜单 ")
    private Long system;

    public Long getSystem() {
        return system;
    }

    public void setSystem(Long system) {
        this.system = system;
    }

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
}
