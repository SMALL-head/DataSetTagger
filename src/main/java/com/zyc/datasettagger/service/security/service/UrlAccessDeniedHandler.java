package com.zyc.datasettagger.service.security.service;

import com.zyc.datasettagger.service.security.entity.web.AuthenticateResponse;
import com.zyc.utils.JSONResponseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * 请求无权限的handler。没有登录认证的情况不会走这个handler
 * @author zyc
 * @version 1.0
 */
@Service
public class UrlAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        AuthenticateResponse resp = new AuthenticateResponse(403,accessDeniedException.getMessage(),null);
        JSONResponseUtils.response(response, resp);
    }
}
