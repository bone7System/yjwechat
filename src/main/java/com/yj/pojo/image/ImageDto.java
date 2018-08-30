package com.yj.pojo.image;

import io.swagger.annotations.ApiModelProperty;

public class ImageDto {

    @ApiModelProperty("描述 ")
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
