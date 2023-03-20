package com.zyc.common.model;

import com.zyc.common.enums.ReturnCode;

import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
public class ResponseData<T extends Serializable> implements Serializable {
    private int code;
    private String error_msg;
    T data;

    public static <T extends Serializable> ResponseData<T> success(T data) {
        return new ResponseData<>(ReturnCode.RC200.getCode(), ReturnCode.RC200.getMsg(), data);
    }

    public static <T extends Serializable> ResponseData<T> fail() {
        return new ResponseData<>(ReturnCode.RC999.getCode(), ReturnCode.RC999.getMsg(), null);
    }

    public static <T extends Serializable> ResponseData<T> fail(String error_msg) {
        return new ResponseData<>(ReturnCode.RC999.getCode(), error_msg, null);
    }

    public static <T extends Serializable> ResponseData<T> generate(ReturnCode rc, T data) {
        return new ResponseData<>(rc.getCode(), rc.getMsg(), data);
    }

    public ResponseData(int code, String error_msg, T data) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
