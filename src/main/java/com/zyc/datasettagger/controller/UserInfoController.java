package com.zyc.datasettagger.controller;

import com.zyc.common.security.entity.User;
import com.zyc.common.security.entity.web.SimpleUserDataModel;
import com.zyc.common.security.entity.web.UserDataModel;
import com.zyc.datasettagger.service.UserService;
import com.zyc.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取用户信息的接口，用户前端请求使用而非security的组成部分
 * @author zyc
 * @version 1.0
 */
@RestController
public class UserInfoController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api/user/info")
    public SimpleUserDataModel getUserInfo() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User userByName = userService.getUserByName(name);
        return Convertor.User2SimpleUserDataModel(userByName, false);
    }
}
