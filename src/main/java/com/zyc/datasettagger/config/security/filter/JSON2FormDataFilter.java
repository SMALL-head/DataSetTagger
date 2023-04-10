package com.zyc.datasettagger.config.security.filter;

import com.zyc.common.constants.URIConstants;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import java.io.IOException;

/**
 * spring security提供的登录方式是表单登录，但是助教提供的登录接口类型是json类型，因此需要用一种手段来解决
 * 这个过滤器当接受
 * @author zyc
 * @version 1.0
 */
@Slf4j
public class JSON2FormDataFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        String contentType = request.getContentType();
        // 1. 如果不是json类型的数据，就直接放行并且return了
        if (!MediaType.APPLICATION_JSON_VALUE.equals(contentType)) {
            filterChain.doFilter(request, response);
            return; // tmd，一开始忘记return了，返回结果无比奇怪。。。。。。
        }
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String requestURI = httpServletRequest.getRequestURI();
        log.debug("[doFilter]-requestURI = {}", requestURI);

        // 2. 如果url是登录url，就重新封装请求，json类型变成
        if (URIConstants.LOGIN_URI.equals(requestURI)) {
            // 2.1 ParameterRequestWrapper是Request一个装饰器。
            // 为什么要使用装饰器？因为内置Request不提供set方法重新setParameter，那就只能重新装饰getter了
            ParameterRequestWrapper convertedRequest = new ParameterRequestWrapper(httpServletRequest);
            String username = convertedRequest.getParameter("username");
            String password = convertedRequest.getParameter("password");
            String contentType1 = convertedRequest.getContentType();
            log.debug("[doFilter]- username = {}, pwd={}, ct={}", username, password, contentType1);
            filterChain.doFilter(convertedRequest, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
