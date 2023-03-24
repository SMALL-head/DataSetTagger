package com.zyc.common.exception;

/**
 * @author zyc
 * @version 1.0
 */
public class BizException extends Exception{

    public BizException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
