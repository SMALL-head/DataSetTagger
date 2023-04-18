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

    /**
     * 通过用户名查找对应的id，返回值Integer可能为空，因此需要进行检查
     * @param username 用户名
     * @return 用户id
     */
    Integer getIdByUsername(String username);

    User getUserByName(String name);
}
