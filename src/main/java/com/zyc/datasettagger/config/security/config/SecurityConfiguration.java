package com.zyc.datasettagger.config.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.security.entity.web.AuthenticateResponse;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
public class SecurityConfiguration {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Bean
    public SecurityFilterChain filterChain1(HttpSecurity httpSecurity,
                                            UserDetailsService userDetailsService,
                                            AccessDeniedHandler accessDeniedHandler,
                                            AuthenticationEntryPoint authenticationEntryPoint,
                                            LogoutSuccessHandler logoutSuccessHandler,
                                            SessionInformationExpiredStrategy sessionInformationExpiredStrategy,
                                            AuthenticationSuccessHandler authenticationSuccessHandler,
                                            AuthenticationFailureHandler authenticationFailureHandler) throws Exception {
        return httpSecurity
            .securityMatcher("/api/**")
            .authorizeHttpRequests(registry -> {
                try {
                    registry
                        .requestMatchers("/api/user/register").permitAll()
                        .requestMatchers("/api/user/login").permitAll()
                        .anyRequest().authenticated(); // 所有其他请求一律需要认证
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .formLogin()
            .loginProcessingUrl("/api/user/login").permitAll() // 前端action中的url，匹配后将会将登录认证信息传给bean认证
            .usernameParameter("username").passwordParameter("password")// 前端表单指定的值
            .successHandler(authenticationSuccessHandler)
            .failureHandler(authenticationFailureHandler)
            .and()
            .logout() // 开启注销功能，默认是开启的，这里调用该方法只是为了获取其对象
            .logoutUrl("/api/user/logout")
            .permitAll()
            .invalidateHttpSession(true) // 默认是true，退出登录后推出session
            .clearAuthentication(true) // 默认是true，清除认证
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(logoutSuccessHandler) //如果前后端分离可以使用handler
            .and()
            .userDetailsService(userDetailsService)
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint) // 自定义未登录逻辑，这样就不会返回登陆界面了
            .and()
            .csrf().disable()
            .sessionManagement()
            .maximumSessions(1) // 只允许一个登录，重复登录会挤掉之前的登录
            .expiredSessionStrategy(sessionInformationExpiredStrategy)
            .maxSessionsPreventsLogin(false)
            .and()
            .and()
            .build();
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
}