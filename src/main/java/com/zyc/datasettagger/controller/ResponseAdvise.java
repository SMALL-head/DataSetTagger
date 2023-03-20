package com.zyc.datasettagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.model.ResponseData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
@RestControllerAdvice
public class ResponseAdvise implements ResponseBodyAdvice<Object> {
    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @SneakyThrows
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       if (body instanceof String) {
           return objectMapper.writeValueAsString(ResponseData.success((String)body));
       }
       // 如果出现异常，会被全局异常处理器捕获并返回一个ResponseData，然后此处就会接到这个对象。这种情况直接返回就行
       if (body instanceof ResponseData) {
           return body;
       }
       return ResponseData.success((Serializable) body);
    }
}
