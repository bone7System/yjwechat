package com.yj.pojo.system.user;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

public class UserUpPasswordDto {
    @NotNull
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("客户端")
    private Long client;
    @NotNull
    @ApiModelProperty("密码")
    private String passWord;
    @NotNull
    @ApiModelProperty("重复密码")
    private String passWord2;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getClient() {
        return client;
    }

    public void setClient(Long client) {
        this.client = client;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord2() {
        return passWord2;
    }

    public void setPassWord2(String passWord2) {
        this.passWord2 = passWord2;
    }
}
