package com.zyc.datasettagger.service.security.service;

import com.zyc.common.security.entity.Role;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.service.security.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 自定义用户认证服务，数据来源为MySQL数据库
 * @author zyc
 * @version 1.0
 */
@Service
@Primary
@Slf4j
public class DBUserDetailService implements UserDetailsService {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("无法找到用户名");
        }
        List<Role> roleList = userMapper.getUserRoleByUid(user.getId());
        user.setRoles(roleList);
        return user;
    }
}
