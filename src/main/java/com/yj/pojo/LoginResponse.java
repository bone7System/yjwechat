package com.yj.pojo;

public class LoginResponse {
    private String token;
    private String message = "此 token 应当以 x-auth-token 属性附在请求的 Header 中";


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