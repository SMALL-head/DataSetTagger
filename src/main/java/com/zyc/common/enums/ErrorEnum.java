package com.zyc.common.enums;

/**
 * 运行时出现的异常。这个枚举类主要用于封装BizException的
 * @author zyc
 * @version 1.0
 */

public enum ErrorEnum {
    MULTIPLE_USERNAME(2003, "用户名重复");

    final int code;
    final String msg;

    ErrorEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
