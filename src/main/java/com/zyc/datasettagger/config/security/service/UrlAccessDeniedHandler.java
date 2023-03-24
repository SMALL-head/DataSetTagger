package com.zyc.datasettagger.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.model.ResponseData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;

/**
 * 请求无权限的handler。没有登录认证的情况不会走这个handler
 * @author zyc
 * @version 1.0
 */
@Service
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseData<Serializable> resp = ResponseData.generate(ReturnCode.RC403, null);
        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        response.getWriter().println(objectMapper.writeValueAsString(resp));
    }
}
