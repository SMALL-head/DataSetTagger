package com.zyc.datasettagger.entity.enums;

/**
 * @author zyc
 * @version 1.0
 */
public enum ReturnCode {
    RC200(200, "请求成功"),
    RC403(403, "没有权限"),
    RC999(999, "操作失败"),
    USERNAME_OR_PASSWORD_ERROR(2003, "用户名或密码错误"),
    NO_AUTHENTICATION(2004, "尚未认证");
    final int code;
    final String msg;

    ReturnCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}
