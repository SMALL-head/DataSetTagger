package com.zyc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
public class JSONResponseUtils {
    public static void response(HttpServletResponse response, Serializable content) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().println(new ObjectMapper().writeValueAsString(content));
    }
}
