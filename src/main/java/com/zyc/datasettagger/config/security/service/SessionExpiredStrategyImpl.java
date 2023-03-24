package com.zyc.datasettagger.config.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.model.ResponseData;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
@Service
public class SessionExpiredStrategyImpl implements SessionInformationExpiredStrategy {
    ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        HttpServletResponse response = event.getResponse();
        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
        ResponseData<Serializable> generate = ResponseData.generate(ReturnCode.SESSION_EXPIRE, null);
        response.getWriter().println(objectMapper.writeValueAsString(generate));
    }
}
