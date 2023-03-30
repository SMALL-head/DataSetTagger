package com.zyc.datasettagger.controller;

import com.zyc.common.constants.Constants;
import com.zyc.common.enums.RoleEnum;
import com.zyc.common.exception.BizException;
import com.zyc.datasettagger.service.UserService;
import com.zyc.common.security.entity.User;
import com.zyc.common.security.entity.web.UserDataModel;
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

    @PostMapping(value = "/api/user/register", produces = Constants.JSON_CONTENT_TYPE_UTF8)
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
}
