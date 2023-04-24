package com.zyc.utils;

import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.model.SampleModel;
import com.zyc.common.model.param.UsernamePassword;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

/**
 * @author zyc
 * @version 1.0
 */
@Slf4j
public class ParamChecker {
    public static void checkUsernamePasswordNull(UsernamePassword usernamePassword) {
        if (usernamePassword == null) {
            log.warn("[checkUsernamePasswordNull]-前端传参为空，检查传参");
            throw new BizException("invalid parameter", ReturnCode.INVALID_INPUT);
        }
        String password = usernamePassword.getPassword();
        String username = usernamePassword.getUsername();
        if (username == null || password == null) {
            log.warn("[checkUsernamePasswordNull]-用户名或密码为空，username={}, password={}", username, password);
            throw new BizException("invalid parameter", ReturnCode.INVALID_INPUT);
        }
    }

    /**
     * 检查sampleModel的dataset_id是否为空
     * @param sampleModel 被检查的sampleModel
     */
    public static void checkSampleModel(SampleModel sampleModel) {
        if (sampleModel == null) {
            log.warn("[checkSampleModel]-前端传参为空，检查传参");
            throw new BizException("invalid parameter", ReturnCode.INVALID_INPUT);
        }
        String datasetId = sampleModel.getDataset_id();
        if (ObjectUtils.isEmpty(datasetId)) {
            log.warn("[checkSampleModel]-dataset_id为空, dataset_id={}", datasetId);
            throw new BizException("dataset_id为空", ReturnCode.INVALID_INPUT);
        }
    }
}
