package com.zyc.datasettagger.service.security.mapper;

import com.zyc.datasettagger.service.security.entity.Role;
import com.zyc.datasettagger.service.security.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.dao.DuplicateKeyException;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    List<Role> getUserRoleByUid(Integer id);

    User loadUserByUsername(String username);

    int addUser(User user) throws DuplicateKeyException;

    int addTaggerRole(@Param("user") User user, @Param("rid") Integer rid);

}
