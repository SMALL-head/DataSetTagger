package com.zyc.datasettagger.service.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.datasettagger.entity.constants.Constants;
import com.zyc.datasettagger.entity.enums.ReturnCode;
import com.zyc.datasettagger.entity.web.ResponseData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseData<Serializable> generate = ResponseData.generate(ReturnCode.NO_AUTHENTICATION, null);
        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        response.getWriter().println(objectMapper.writeValueAsString(generate));
    }
}
