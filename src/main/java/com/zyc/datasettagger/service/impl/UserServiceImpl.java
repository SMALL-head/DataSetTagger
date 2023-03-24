package com.zyc.datasettagger.service.impl;

import com.zyc.common.enums.ErrorEnum;
import com.zyc.common.enums.RoleEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.datasettagger.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
    public User addUser(String username, String password, String phone, String email, RoleEnum roleEnum) throws BizException {
        log.info("[taggerRegister]-注册用户{}", username);
        User user = new User("{noop}" + password, username, phone, email);
        try {
            userMapper.addUser(user);
        } catch (DuplicateKeyException sqlException) {
            throw new BizException(ErrorEnum.MULTIPLE_USERNAME.getMsg());
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
}
