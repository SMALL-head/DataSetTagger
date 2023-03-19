package com.zyc.datasettagger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.datasettagger.entity.constants.Constants;
import com.zyc.datasettagger.entity.enums.RoleEnum;
import com.zyc.datasettagger.entity.exception.BizException;
import com.zyc.datasettagger.service.UserService;
import com.zyc.datasettagger.service.security.entity.User;
import com.zyc.datasettagger.service.security.entity.web.AuthenticateResponse;
import com.zyc.datasettagger.service.security.entity.web.UserDataModel;
import com.zyc.datasettagger.service.security.mapper.UserMapper;
import com.zyc.utils.Convertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
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
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/tagger/register", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public UserDataModel taggerRegister(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 String phone,
                                 String email) throws BizException {
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
            throw new BizException("账号或密码不能为空");
        }
        User user = userService.addUser(username, password, phone, email, RoleEnum.TAGGER);
        return Convertor.User2UserDataModel(user);
    }

    @PostMapping(value = "/api/publisher/register", produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public UserDataModel publisherRegister(@RequestParam("username") String username,
                                           @RequestParam("password") String password,
                                           String phone,
                                           String email) throws BizException {
        if (ObjectUtils.isEmpty(username) || ObjectUtils.isEmpty(password)) {
            throw new BizException("账号或密码不能为空");
        }
        User user = userService.addUser(username, password, phone, email, RoleEnum.PUBLISHER);
        return Convertor.User2UserDataModel(user);
    }

}
