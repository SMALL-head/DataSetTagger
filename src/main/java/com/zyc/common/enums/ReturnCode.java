package com.zyc.common.enums;

/**
 * @author zyc
 * @version 1.0
 */
public enum ReturnCode {
    RC200(200, "请求成功"),
    INVALID_ID(400, "非法id"),
    RC403(403, "没有权限"),
    DATA_NOT_FOUND(404, "未找到满足条件的数据"),
    INVALID_INPUT(405, "非法入参"),

    USER_NOT_FOUND(2004, "无法找到对应用户名"),
    RC999(999, "操作失败"),
    USERNAME_OR_PASSWORD_ERROR(2003, "用户名或密码错误！"),
    NO_AUTHENTICATION(2004, "尚未认证"),

    MULTIPLE_USERNAME(2006, "用户名重复"),
    SESSION_EXPIRE(2005, "会话过期，请重新登录"),
    ;
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
