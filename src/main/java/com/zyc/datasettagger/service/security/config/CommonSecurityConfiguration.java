package com.zyc.datasettagger.service.security.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.datasettagger.entity.constants.Constants;
import com.zyc.datasettagger.entity.enums.ReturnCode;
import com.zyc.datasettagger.service.security.entity.User;
import com.zyc.datasettagger.service.security.entity.web.AuthenticateResponse;
import com.zyc.datasettagger.service.security.entity.web.UserDataModel;
import com.zyc.datasettagger.service.security.mapper.UserMapper;
import com.zyc.utils.Convertor;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.HashMap;
import java.util.Map;

@Configuration
@Order(3)
public class CommonSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain3(HttpSecurity httpSecurity,
                                            LogoutSuccessHandler logoutSuccessHandler) throws Exception {
        httpSecurity
            .securityMatcher("/common/**")
            .logout(). // 开启注销功能，默认是开启的，这里调用该方法只是为了获取其对象
            logoutUrl("/common/logout").
            invalidateHttpSession(true). // 默认是true，退出登录后推出session
            clearAuthentication(true). // 默认是true，清除认证
            deleteCookies("JSESSIONID").
            logoutSuccessHandler(logoutSuccessHandler). //如果前后端分离可以使用handler
            and()
            .csrf().disable();
        return httpSecurity.build();
    }
}
