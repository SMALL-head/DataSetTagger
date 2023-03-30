package com.zyc.datasettagger.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.security.entity.web.AuthenticateResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author zyc
 * @version 1.0
 */
@Service
public class AuthenticationFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        AuthenticateResponse authenticateResponse = new AuthenticateResponse(ReturnCode.USERNAME_OR_PASSWORD_ERROR.getCode(), ReturnCode.USERNAME_OR_PASSWORD_ERROR.getMsg(), null);
        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        response.getWriter().println(new ObjectMapper().writeValueAsString(authenticateResponse));
    }
}
