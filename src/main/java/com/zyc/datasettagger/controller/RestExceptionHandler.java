package com.zyc.datasettagger.controller;

import com.zyc.common.exception.BizException;
import com.zyc.common.model.ResponseData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 内部服务器代码运行时，如果有异常抛出到Controller层了，将会被这个Controller接住
 * 然后返回统一返回值类型ResponseData
 * 内部逻辑代码的所有异常最好都进行BizException封装，不要用原始的Exception，因为原始的Exception类型报错信息不友好
 * 使用BizException重新封装后，前端无脑展示就好
 * @author zyc
 * @version 1.0
 */
@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseData<String> exception(BizException e) {
        return ResponseData.generate(e.getReturnCode(), e.getMessage());
    }
}
