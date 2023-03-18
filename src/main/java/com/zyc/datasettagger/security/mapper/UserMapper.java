package com.zyc.datasettagger.security.mapper;

import com.zyc.datasettagger.security.entity.Role;
import com.zyc.datasettagger.security.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer id);

    User loadUserByUsername(String username);
}
