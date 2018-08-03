package com.yj.pojo;

import com.yj.domain.user.model.UserDetail;

public class LoginResponse {
    private String token;
    private String message = "此 token 应当以 x-auth-token 属性附在请求的 Header 中";

    UserDetail user;

    public UserDetail getUser() {
        return user;
    }

    public void setUser(UserDetail user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
