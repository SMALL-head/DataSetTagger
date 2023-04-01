package com.zyc.datasettagger.controller;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.DataSetModel;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.datasettagger.service.UserService;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
@Slf4j
public class DataSetController {
    DataSetService dataSetService;

    UserService userService;

    @Autowired
    public void setDataSetService(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/dataset", produces = MediaType.APPLICATION_JSON_VALUE)
    public DataSetModel addDataSet(String desc,
                                   @RequestParam("example_type") String sampleType,
                                   @RequestParam("tag_tpe") String tagType) throws EnumAcquireException, BizException {
        // 1. 封装datasetInfo
        DataSetInfo dataSetInfo = new DataSetInfo();
        dataSetInfo.setDesc(desc);
        dataSetInfo.setSampleType(SampleTypeEnum.getEnumByName(sampleType));
        dataSetInfo.setTagType(TagTypeEnum.getEnumByName(tagType));
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        log.info("[addDataSet]-添加dataset，用户名为{}", username);
        // 2. 通过security上下文获取到当前用户名，然后反查用户id。
        // 这一步操作主要两个目的，其一是验证用户是否存在，其二是继续封装datasetInfo
        Integer idByUsername = userService.getIdByUsername(username);
        if (idByUsername == null) {
            log.error("[addDataSet]-用户名{}对应的id为空，对前端传参进行检查", username);
            throw new BizException("用户名{%s}不存在，无法添加对应的数据集");
        }

        dataSetInfo.setPublisherId(idByUsername);
        String datasetId = UUID.randomUUID().toString();
        dataSetInfo.setDatasetId(datasetId);

        int i = dataSetService.insertDataSet(dataSetInfo);

        if (i == 1) {
            log.info("[addDataSet]-success-向数据集中添加一条数据，publisher={}", username);
        } else {
            log.warn("[addDataSet]-fail-向数据集添加数据失败, publisher={}", username);
        }
        return DataSetConvertor.DataSetInfo2Model(dataSetService.getDataSetByDataSetId(datasetId));
    }
}