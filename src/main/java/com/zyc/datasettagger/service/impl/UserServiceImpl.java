package com.zyc.datasettagger.service.impl;

import com.zyc.common.enums.ErrorEnum;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.enums.RoleEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.datasettagger.service.UserService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author zyc
 * @version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User addUser(String username, String rawPassword, String phone, String email, RoleEnum roleEnum) throws BizException {
        log.info("[taggerRegister]-注册用户{}", username);
        User user = new User("{noop}" + rawPassword, username, phone, email);
        try {
            userMapper.addUser(user);
        } catch (DuplicateKeyException sqlException) {
            throw new BizException(ErrorEnum.MULTIPLE_USERNAME.getMsg(), ReturnCode.MULTIPLE_USERNAME);
        }

        user = userMapper.loadUserByUsername(username);
        log.info("[taggerRegister]-写入数据库成功，uid={}, username={}", user.getId(), user.getUsername());
        int i = userMapper.addTaggerRole(user, roleEnum.getRid());
        if (i == 1) {
            log.info("[taggerRegister]-新用户{}权限更新,为{}", user.getUsername(), roleEnum.getName());
        } else {
            log.warn("[taggerRegister]-新用户{}权限更新失败", user.getUsername());
        }
        return user;
    }

    @Override
    public Integer getIdByUsername(String username) {
        if (ObjectUtils.isEmpty(username)) {
            log.warn("[getIdByUsername]-username传参为空");
            return null;
        }
        Integer idByUsername = userMapper.getIdByUsername(username);
        if (idByUsername == null) {
            log.warn("[getIdByUsername]-用户名{}不存在，故无法找到对应的id", username);
            return null;
        }
        return idByUsername;
    }
}
