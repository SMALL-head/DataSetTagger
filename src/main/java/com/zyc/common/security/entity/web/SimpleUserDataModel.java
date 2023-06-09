package com.zyc.common.security.entity.web;

import java.io.Serializable;

/**
 * 前端模型：精简版用户信息
 * @author zyc
 * @version 1.0
 */
public class SimpleUserDataModel implements Serializable {
    String username;

    String uid;

    public SimpleUserDataModel() {
    }

    public SimpleUserDataModel(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
