package com.zyc.datasettagger.service.security.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.datasettagger.entity.constants.Constants;
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
@Order(1)
public class TaggerSecurityConfiguration {
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
                                            LogoutSuccessHandler logoutSuccessHandler) throws Exception {

        httpSecurity.authorizeHttpRequests(registry -> {
            try {
                registry.
                    requestMatchers("/index").permitAll(). // 先指定放行的请求，然后在拦截其他所有请求（白名单指定）
                    requestMatchers("/login2").permitAll().
                    requestMatchers("/api/tagger/**").hasRole("TAGGER").
//                    requestMatchers("/api/publisher/**").hasRole("PUBLISHER").
//    anyRequest().authenticated(). // 所有其他请求一律需要认证
    and().
                    formLogin().
                    loginProcessingUrl("/api/tagger/login"). // 前端action中的url，匹配后将会将登录认证信息传给bean认证
                    usernameParameter("username").passwordParameter("password").// 前端表单指定的值
                    successHandler((request, response, authentication) -> {
                    // 认证成功回调的处理方法，这里为向前端返回成功信息
                    User user = userMapper.loadUserByUsername(authentication.getName());
                    UserDataModel userDataModel = Convertor.User2UserDataModel(user);
                    // 设置response响应为json格式后，拿到response的PrintWriter，直接写入就好
                    response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
                    AuthenticateResponse authenticateResponse = new AuthenticateResponse(200, "", userDataModel);
                    response.getWriter().println(new ObjectMapper().writeValueAsString(authenticateResponse));
                }).
                    failureHandler(((request, response, exception) -> {
                        Map<String, Object> result = new HashMap<>();
                        result.put("msg", exception.getMessage());
                        result.put("status", 403);
                        String json = new ObjectMapper().writeValueAsString(result);
                        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
                        response.getWriter().println(json);
                    })).
                    and(). // 注销登录配置
                    logout(). // 开启注销功能，默认是开启的，这里调用该方法只是为了获取其对象
                    permitAll().
                    invalidateHttpSession(true). // 默认是true，退出登录后推出session
                    clearAuthentication(true). // 默认是true，清除认证
                    logoutSuccessHandler(logoutSuccessHandler). //如果前后端分离可以使用handler
                    and()
                    .csrf().disable()
                    .sessionManagement()
                    .maximumSessions(1) // 只允许一个登录，重复登录会挤掉之前的登录
                    .expiredSessionStrategy(event -> {
                        HttpServletResponse response = event.getResponse();
                        String resp = generateResp(200, "当前会话失效，请重新登录");
                        response.setContentType(Constants.JSON_CONTENT_TYPE_UTF8);
                        response.getWriter().println(resp);
                    })
                    .maxSessionsPreventsLogin(false); // 如果自定义UserDetails需要重写equals和hashcode
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        httpSecurity.userDetailsService(userDetailsService);
        httpSecurity.exceptionHandling()
            .accessDeniedHandler(accessDeniedHandler)
            .authenticationEntryPoint(authenticationEntryPoint);// 自定义未登录逻辑，这样就不会返回登陆界面了

        return httpSecurity.build();
    }

//    @Bean
//    public PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    private String generateResp(int statusCode, String msg) throws JsonProcessingException {
        Map<String, Object> resMap = new HashMap<>();
        resMap.put("status", statusCode);
        resMap.put("msg", msg);
        return new ObjectMapper().writeValueAsString(resMap);
    }
}
