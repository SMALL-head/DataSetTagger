package com.zyc.datasettagger.service.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.security.entity.web.AuthenticateResponse;
import com.zyc.datasettagger.service.security.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

@Configuration
@Order(2)
@Slf4j
public class PublisherSecurityConfiguration {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Bean
    public SecurityFilterChain filterChain2(HttpSecurity httpSecurity,
                                            UserDetailsService userDetailsService,
                                            AccessDeniedHandler accessDeniedHandler,
                                            AuthenticationEntryPoint authenticationEntryPoint,
                                            SessionInformationExpiredStrategy sessionInformationExpiredStrategy,
                                            AuthenticationSuccessHandler authenticationSuccessHandler) throws Exception {
        httpSecurity
            .securityMatcher("/api/publisher/**")
            .authorizeHttpRequests(registry -> {
                try {
                    registry
                        .requestMatchers("/api/publisher/register").permitAll()
                        .requestMatchers("/api/publisher/**").hasRole("PUBLISHER").
                        anyRequest().authenticated(); // 所有其他请求一律需要认证
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            })
            .formLogin()
            .loginProcessingUrl("/api/publisher/login").permitAll()// 前端action中的url，匹配后将会将登录认证信息传给bean认证
            .usernameParameter("username").passwordParameter("password")// 前端表单指定的值
            .successHandler(authenticationSuccessHandler)
            .failureHandler(((request, response, exception) -> {
                AuthenticateResponse authenticateResponse = new AuthenticateResponse(ReturnCode.USERNAME_OR_PASSWORD_ERROR.getCode(), ReturnCode.USERNAME_OR_PASSWORD_ERROR.getMsg(), null);
                response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
                response.getWriter().println(new ObjectMapper().writeValueAsString(authenticateResponse));
            })).and()
            .csrf().disable()
            .sessionManagement()
            .maximumSessions(1) // 只允许一个登录，重复登录会挤掉之前的登录
            .expiredSessionStrategy(sessionInformationExpiredStrategy)
            .maxSessionsPreventsLogin(false); // 如果自定义UserDetails需要重写equals和hashcode

        httpSecurity
            .userDetailsService(userDetailsService)
            .exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint);// 自定义未登录逻辑，这样就不会返回登陆界面了

        return httpSecurity.build();
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }
}
