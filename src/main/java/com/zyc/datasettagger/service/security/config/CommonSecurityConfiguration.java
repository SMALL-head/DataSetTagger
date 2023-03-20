package com.zyc.datasettagger.service.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

@Configuration
@Order(3)
public class CommonSecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain3(HttpSecurity httpSecurity,
                                            LogoutSuccessHandler logoutSuccessHandler) throws Exception {
        httpSecurity
            .securityMatcher("/common/**")
            .logout() // 开启注销功能，默认是开启的，这里调用该方法只是为了获取其对象
            .logoutUrl("/common/logout")
            .invalidateHttpSession(true) // 默认是true，退出登录后推出session
            .clearAuthentication(true) // 默认是true，清除认证
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler) //如果前后端分离可以使用handler
            .and()
            .csrf().disable();
        return httpSecurity.build();
    }
}
