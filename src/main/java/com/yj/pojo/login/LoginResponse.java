package com.yj.pojo.login;

import com.yj.domain.user.model.Menu;
import com.yj.domain.user.model.UserDetail;

import java.util.List;

public class LoginResponse {
    private String token;
    private String message = "此 token 应当以 x-auth-token 属性附在请求的 Header 中";

    UserDetail user;
    private List<Menu> mens;

    public List<Menu> getMens() {
        return mens;
    }

    public void setMens(List<Menu> mens) {
        this.mens = mens;
    }

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
