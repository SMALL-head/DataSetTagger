package com.zyc.datasettagger.controller.component;

import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 内部服务器代码运行时，如果有异常抛出到Controller层了，将会被这个Controller接住
 * 然后返回统一返回值类型ResponseData
 * 内部逻辑代码的所有异常最好都进行BizException封装，不要用原始的Exception，因为原始的Exception类型报错信息不友好
 * 使用BizException重新封装后，前端无脑展示就好
 * @author zyc
 * @version 1.0
 */
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    @ExceptionHandler(BizException.class)
    public ResponseData<String> bizException(BizException e) {
        log.warn("[exception]-捕获到异常,e={}", e.getMessage());
        return ResponseData.generate(e.getReturnCode(), e.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseData<String> sqlConstraintException(SQLIntegrityConstraintViolationException e) {
        log.warn("[sqlConstraintException]-捕获到异常, e.message={}, e.state={}", e.getMessage(), e.getSQLState());
        return ResponseData.generate(ReturnCode.RC999, "");
    }

    @ExceptionHandler(EnumAcquireException.class)
    public ResponseData<String> enumAcquireException(EnumAcquireException e) {
        log.warn("[enumAcquireException]-捕获到异常, e.message={}", e.getMessage());
        return ResponseData.generate(ReturnCode.RC999, e.getMessage());
    }

    /**
     * “Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error:xxxx”
     * 添加该异常捕获器主要是希望到参数的JSON转换失败时抛出的异常
     * @param e HttpMessageNotReadableException异常对象
     * @return 通用返回结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseData<String> HttpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.warn("[HttpMessageNotReadableExceptionHandler]-捕获到异常，e.message={}", e.getMessage());
        return ResponseData.generate(ReturnCode.INVALID_INPUT, e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseData<String> MethodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.warn("[MethodArgumentTypeMismatchExceptionHandler]-捕获到异常，e.message={}", e.getMessage());
        return ResponseData.generate(ReturnCode.INVALID_INPUT, "参数类型错误");
    }
}
