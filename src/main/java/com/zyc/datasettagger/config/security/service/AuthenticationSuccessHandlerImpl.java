package com.zyc.datasettagger.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.security.entity.User;
import com.zyc.common.security.entity.web.AuthenticateResponse;
import com.zyc.common.security.entity.web.UserDataModel;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.utils.Convertor;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zyc
 * @version 1.0
 */
@Component
@Slf4j
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user = userMapper.loadUserByUsername(authentication.getName()); // 查找用户用户打日志

        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        AuthenticateResponse authenticateResponse = new AuthenticateResponse(200, "登录成功", null);
        response.getWriter().println(new ObjectMapper().writeValueAsString(authenticateResponse));

        log.info("[onAuthenticationSuccess]-认证成功-username={}", user.getUsername());
    }
}
