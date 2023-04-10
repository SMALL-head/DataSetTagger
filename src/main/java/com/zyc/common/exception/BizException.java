package com.zyc.common.exception;

import com.zyc.common.enums.ReturnCode;

/**
 * @author zyc
 * @version 1.0
 */
public class BizException extends RuntimeException{
    ReturnCode returnCode;
    public BizException(String message, ReturnCode returnCode) {
        super(message);
        this.returnCode = returnCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public ReturnCode getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(ReturnCode returnCode) {
        this.returnCode = returnCode;
    }
}
