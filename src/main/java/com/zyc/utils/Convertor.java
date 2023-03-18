package com.zyc.utils;

import com.zyc.datasettagger.security.entity.User;
import com.zyc.datasettagger.security.entity.web.UserDataModel;
import org.springframework.beans.BeanUtils;

/**
 * 工具类：前后端数据模型的转换
 * @author zyc
 * @version 1.0
 */

public class Convertor {
    public static UserDataModel User2UserDataModel(User user) {
        UserDataModel userDataModel = new UserDataModel();
        BeanUtils.copyProperties(user, userDataModel);
        userDataModel.set_id(user.getId().toString());
        return userDataModel;
    }
}