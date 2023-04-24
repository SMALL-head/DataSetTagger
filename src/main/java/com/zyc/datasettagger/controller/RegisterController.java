package com.zyc.datasettagger.controller;

import com.zyc.common.constants.Constants;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.enums.RoleEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.model.param.UsernamePassword;
import com.zyc.common.security.entity.web.SimpleUserDataModel;
import com.zyc.datasettagger.service.UserService;
import com.zyc.common.security.entity.User;
import com.zyc.common.security.entity.web.UserDataModel;
import com.zyc.utils.Convertor;
import com.zyc.utils.ParamChecker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

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
    public SimpleUserDataModel taggerRegister(@RequestBody UsernamePassword usernamePassword) throws BizException {
        // 1. 检查账号或密码是否为空，若为空直接出异常
        ParamChecker.checkUsernamePasswordNull(usernamePassword);

        String username = usernamePassword.getUsername();
        String password = usernamePassword.getPassword();
        User user = userService.addUser(username, password, null, null, RoleEnum.TAGGER);
        return Convertor.User2SimpleUserDataModel(user, false);
    }
}
