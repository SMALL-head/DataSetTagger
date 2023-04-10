package com.zyc.common.model.param;

import java.io.Serializable;

/**
 * POST请求的请求体，此处用于注册。注意，登录是通过request对象直接拿到，而不是通过这个类反序列化
 * 因此这个类只用于注册
 * @author zyc
 * @version 1.0
 */
public class UsernamePassword implements Serializable {
    String username;
    String password;

    public UsernamePassword() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
