package com.zyc.common.exception;

import java.io.IOException;

/**
 * 转化类时出现的错误
 * @author zyc
 * @version 1.0
 */
public class ConvertException extends IOException {
    public ConvertException(String message, Class<?> raw, Class<?> res) {
        super(raw.getCanonicalName() + "转化为" + res.getCanonicalName() + "异常：" + message);
    }
}
