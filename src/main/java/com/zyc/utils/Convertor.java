package com.zyc.utils;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.entity.DataSetEntity;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.security.entity.User;
import com.zyc.common.security.entity.web.SimpleUserDataModel;
import com.zyc.common.security.entity.web.UserDataModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * 工具类：前后端数据模型的转换
 * @author zyc
 * @version 1.0
 */
@Slf4j
public class Convertor {
    public static SimpleUserDataModel User2SimpleUserDataModel(User user, boolean passwordDisplay) {
        SimpleUserDataModel userDataModel = new SimpleUserDataModel();
        BeanUtils.copyProperties(user, userDataModel);

        return userDataModel;
    }

    public static UserDataModel User2UserDataModel(User user) {
        UserDataModel userDataModel = new UserDataModel();
        userDataModel.setUsername(user.getUsername());
        userDataModel.setEmail(user.getEmail());
        userDataModel.setPhone(user.getPhone());
        userDataModel.set_id(user.getId()+"");
        return  userDataModel;
    }
}
