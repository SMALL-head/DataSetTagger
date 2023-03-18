package com.zyc.datasettagger.security.entity.web;

import java.io.Serializable;

/**
 * 前端模型：返回给前端的用户信息，包含username, password, phone, email
 *
 * @author zyc
 * @version 1.0
 */

public class UserDataModel implements Serializable {
    String _id;
    String username;
    String password;
    String phone;
    String email;

    public UserDataModel(String _id, String username, String password, String phone, String email) {
        this._id = _id;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public UserDataModel() {
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
