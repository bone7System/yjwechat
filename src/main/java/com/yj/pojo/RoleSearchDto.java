package com.yj.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class RoleSearchDto {
    
    @ApiModelProperty("角色id")
    private Long id;

    @ApiModelProperty("角色描述")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
