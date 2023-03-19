package com.zyc.datasettagger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.datasettagger.entity.enums.RoleEnum;
import com.zyc.datasettagger.service.security.entity.User;
import com.zyc.datasettagger.service.security.entity.web.AuthenticateResponse;
import com.zyc.datasettagger.service.security.mapper.UserMapper;
import com.zyc.utils.Convertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
@Slf4j
public class RegisterController {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @PostMapping("/api/tagger/register")
    public String taggerRegister(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 String phone,
                                 String email) throws JsonProcessingException {
        User user = addUser(username, password, phone, email, RoleEnum.TAGGER);
        AuthenticateResponse response = new AuthenticateResponse(200, "", Convertor.User2UserDataModel(user));
        return new ObjectMapper().writeValueAsString(response);
    }

    @PostMapping("/api/publisher/register")
    public String publisherRegister(@RequestParam("username") String username,
                                    @RequestParam("password") String password,
                                    String phone,
                                    String email) throws JsonProcessingException {
        User user = addUser(username, password, phone, email, RoleEnum.PUBLISHER);
        AuthenticateResponse response = new AuthenticateResponse(200, "", Convertor.User2UserDataModel(user));
        return new ObjectMapper().writeValueAsString(response);
    }

    private User addUser(String username, String password, String phone, String email, RoleEnum roleEnum) {
        log.info("[taggerRegister]-注册用户{}", username);
        User user = new User(password, username, phone, email);
        userMapper.addUser(user);
        user = userMapper.loadUserByUsername(username);
        log.info("[taggerRegister]-写入数据库成功，uid={}, username={}", user.getId(), user.getUsername());
        int i = userMapper.addTaggerRole(user, roleEnum.getRid());
        if (i == 1) {
            log.info("[taggerRegister]-新用户{}权限更新,为tagger", user.getUsername());
        } else {
            log.warn("[taggerRegister]-新用户{}权限更新失败", user.getUsername());
        }
        return user;
    }
}
