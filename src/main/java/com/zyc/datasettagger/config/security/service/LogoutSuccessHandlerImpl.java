package com.zyc.datasettagger.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.model.ResponseData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author zyc
 * @version 1.0
 */
@Component
public class LogoutSuccessHandlerImpl implements LogoutSuccessHandler {
    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        ResponseData<String> success = ResponseData.success("注销成功");
        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        response.getWriter().println(objectMapper.writeValueAsString(success));
    }
}
