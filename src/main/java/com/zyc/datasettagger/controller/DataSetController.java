package com.zyc.datasettagger.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zyc.common.constants.Constants;
import com.zyc.common.data.DataSetInfo;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.DataSetModel;
import com.zyc.common.model.page.DatasetListPage;
import com.zyc.common.model.page.ListPage;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.datasettagger.service.UserService;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    ObjectMapper objectMapper;

    @Autowired
    public void setDataSetService(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping(value = "/api/dataset", produces = MediaType.APPLICATION_JSON_VALUE)
    public DataSetModel addDataSet(@RequestBody DataSetModel param) throws EnumAcquireException, BizException {

        String desc = param.getDesc();
        String sampleType = param.getSample_type();
        String tagType = param.getTag_type();
        String name = param.getName();

        // 1. 封装datasetInfo
        DataSetInfo dataSetInfo = new DataSetInfo();
        if (ObjectUtils.isEmpty(desc) || ObjectUtils.isEmpty(sampleType) || ObjectUtils.isEmpty(tagType)) {
            throw new BizException("数据集描述或样本类型或标记类型不能为空", ReturnCode.INVALID_INPUT);
        }
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
            throw new BizException("用户名{%s}不存在，无法添加对应的数据集", ReturnCode.USER_NOT_FOUND);
        }

        dataSetInfo.setPublisherId(idByUsername);
        String datasetId = UUID.randomUUID().toString();
        dataSetInfo.setDatasetId(datasetId);
        dataSetInfo.setName(name);
        dataSetInfo.setPublisherName(username);

        int i = dataSetService.insertDataSet(dataSetInfo);

        if (i == 1) {
            log.info("[addDataSet]-success-向数据集中添加一条数据，publisher={}", username);
        } else {
            log.warn("[addDataSet]-fail-向数据集添加数据失败或进行了无变化更新, publisher={}", username);
        }
        return DataSetConvertor.DataSetInfo2Model(dataSetService.getDataSetByDataSetId(datasetId));
    }

    @RequestMapping(value = "/api/datasets", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatasetListPage getAllDataSet(Integer page_num, Integer page_size, Integer publisher_id) throws BizException {
        if (page_num == null || page_size == null) {
            log.warn("[getAllDataSet]-非法参数传入 - page_num = {}, page_size={}", page_num, page_size);
            throw new BizException("必须传入分页信息page_num和page_size", ReturnCode.INVALID_INPUT);
        }

        // 进行无差别全量查询，查询结果转化为DataSetModel
        ListPage<DataSetInfo> allDataSet = dataSetService.getAllDataSetInfoByLimitation(page_num, page_size, publisher_id);
        List<DataSetInfo> pageContent = allDataSet.getPageContent();
        List<DataSetModel> collect = pageContent.stream().map(DataSetConvertor::DataSetInfo2Model).toList();
        return new DatasetListPage(allDataSet.getCurPage(), allDataSet.getPageSize(), collect, allDataSet.getLimitation(), allDataSet.getTotal());
    }


    @RequestMapping(value = "/api/dataset/{id}", method = RequestMethod.PUT)
    public DataSetModel updateDataset(@PathVariable("id") String id, @RequestBody DataSetModel dataSetModel) throws JsonProcessingException {
        if (dataSetModel == null) {
            log.warn("[updateDataset]-datasetModel为空");
            throw new BizException("数据集信息不能为空", ReturnCode.INVALID_INPUT);
        }
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[updateDataset]-数据集id不能为空");
            throw new BizException("数据集信息不能为空", ReturnCode.INVALID_ID);
        }
        log.info("[updateDataset]-接收到body数据{}", objectMapper.writer().writeValueAsString(dataSetModel));
        DataSetInfo dataSetInfo = null;
        try {
            dataSetInfo = DataSetConvertor.dataSetModel2Info(dataSetModel);
        } catch (EnumAcquireException e) {
            throw new BizException("样本类型或者数据集类型有误", ReturnCode.INVALID_INPUT);
        }

        // 卫语句结束，下面是业务逻辑

        dataSetInfo.setDatasetId(id); // 实际上我们可以直接把这个信息放在请求体中，但是助教定死了一个rest请求。。。

        // 表面上助教要求传递publisherId，但这实际上是一个比较危险的方式，我这里的id是根据用户上下文来获取的
        String operatorName = SecurityContextHolder.getContext().getAuthentication().getName();
        dataSetInfo.setPublisherId(userService.getIdByUsername(operatorName));

        // 更新并回查数据返回给前端
        dataSetService.updateDataset(dataSetInfo);
        DataSetInfo dataSetByDataSetId = dataSetService.getDataSetByDataSetId(id);
        return DataSetConvertor.DataSetInfo2Model(dataSetByDataSetId);
    }

    @RequestMapping(value = "/api/dataset/{id}", method = RequestMethod.DELETE, produces = Constants.JSON_CONTENT_TYPE_UTF8)
    public String deleteDataSetById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[deleteDataSetById]-数据集id不能为空");
            throw new BizException("数据集id不能为空", ReturnCode.INVALID_ID);
        }

        // 权限校验
        String nameInContext = SecurityContextHolder.getContext().getAuthentication().getName();
        DataSetInfo dataSetByDataSetId = dataSetService.getDataSetByDataSetId(id);
        String publisherName = dataSetByDataSetId.getPublisherName();
        if (!publisherName.equals(nameInContext)) {
            log.warn("[deleteDataSetById]-数据集owner为{}， 操作者为{}", publisherName, nameInContext);
            throw new BizException("无权限", ReturnCode.RC403);
        }

        // 删除操作
        int i = dataSetService.deleteDatasetById(id);
        if (i == 0) {
            log.info("[deleteDataSetById]-未找到指定待删除的数据集");
            throw new BizException("未找到指定待删除的数据集", ReturnCode.DATA_NOT_FOUND);
        }
        return "删除成功";
    }

    @RequestMapping(value = "/api/dataset/{id}", method = RequestMethod.GET)
    public DataSetModel getDatasetById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[getDatasetById]-接收到的id为空，请求用户为{}", SecurityContextHolder.getContext().getAuthentication().getName());
            throw new BizException("数据集id不能为空", ReturnCode.INVALID_ID);
        }
        DataSetInfo dataSetByDataSetId = dataSetService.getDataSetByDataSetId(id);
        if (dataSetByDataSetId == null) {
            log.warn("[getDatasetById]-无法查找对应的数据集, id={}", id);
            throw new BizException("查找数据集为空", ReturnCode.DATA_NOT_FOUND);
        }

        return DataSetConvertor.DataSetInfo2Model(dataSetByDataSetId);
    }
}
