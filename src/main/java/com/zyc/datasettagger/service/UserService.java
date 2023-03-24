package com.zyc.datasettagger.service;

import com.zyc.common.enums.RoleEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.security.entity.User;

/**
 * @author zyc
 * @version 1.0
 */
public interface UserService {
    User addUser(String username, String password, String phone, String email, RoleEnum roleEnum) throws BizException;
}
