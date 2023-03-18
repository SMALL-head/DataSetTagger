package com.zyc.datasettagger.security.entity.web;

import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
public class AuthenticateResponse implements Serializable {
    int code;
    String error_msg;

    UserDataModel data;

    public AuthenticateResponse(int code, String error_msg, UserDataModel data) {
        this.code = code;
        this.error_msg = error_msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }

    public UserDataModel getData() {
        return data;
    }

    public void setData(UserDataModel data) {
        this.data = data;
    }
}
