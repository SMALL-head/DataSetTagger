package com.zyc.datasettagger.controller;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.data.SampleInfo;
import com.zyc.common.entity.SampleEntity;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.ListPage;
import com.zyc.common.model.SampleListPage;
import com.zyc.common.model.SampleModel;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.datasettagger.service.SampleService;
import com.zyc.utils.Convertor;
import com.zyc.utils.ParamChecker;
import com.zyc.utils.convertor.SampleConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@RestController
@Slf4j
public class SampleController {
    SampleService sampleService;
    DataSetService dataSetService;

    @Autowired
    public void setSampleService(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Autowired
    public void setDataSetService(DataSetService dataSetService) {
        this.dataSetService = dataSetService;
    }

    @PostMapping("/api/sample")
    public SampleModel addSample(@RequestBody SampleModel sample) throws EnumAcquireException {
        ParamChecker.checkSampleModel(sample);

        // 1. 根据datasetId查询对应的tag和sample类型，并设置在sampleInfo中，之后直接调用方法set进去就好
        String datasetId = sample.getDataset_id();
        DataSetInfo dataSetInfo = dataSetService.getDataSetByDataSetId(datasetId);
        sample.setTag_type(dataSetInfo.getTagType().getName());
        sample.setSample_type(dataSetInfo.getSampleType().getName());
        SampleInfo sampleInfo = SampleConvertor.model2Info(sample);
        String sampleId = sampleService.addSample(sampleInfo);

        // 插入成功后返回自动生成的UUID，把这个UUID重新设置会SampleModel中
        sample.set_id(sampleId);

        return sample;
    }

    @GetMapping(value = "/api/samples", produces = MediaType.APPLICATION_JSON_VALUE)
    public SampleListPage getAllSample(@RequestParam("page_num") Integer pageNum,
                                       @RequestParam("page_size") Integer limitation,
                                       @RequestParam("dataset_id") String dataset_id) {
        if (pageNum == null || limitation == null) {
            log.error("[getAllSample]-getAllSample请求参数有误，pageNum和limitation不能为null");
            throw new BizException("参数错误", ReturnCode.INVALID_INPUT);
        }
        if (dataset_id == null) {
            // 没有指定dataset，全量展示？？
            return new SampleListPage(1, 1, new ArrayList<>(), limitation, 0);
        } else {
            DataSetInfo dataSetByDataSetId = dataSetService.getDataSetByDataSetId(dataset_id);
            if (dataSetByDataSetId == null) {
                log.warn("[getAllSample]-未找到对应的dataset_id={}对应的dataset", dataset_id);
                return new SampleListPage(1, 1, new ArrayList<>(), limitation, 0);
            }

            ListPage<SampleInfo> sampleEntityListPage = sampleService.getSampleByLimitation(dataset_id, pageNum, limitation);
            List<SampleInfo> sampleEntityList = sampleEntityListPage.getPageContent();
            List<SampleModel> sampleModelList = sampleEntityList.stream().map(SampleConvertor::data2Model).toList();
            return new SampleListPage(sampleEntityListPage.getCurPage(), sampleEntityListPage.getPageSize(), sampleModelList, sampleEntityListPage.getLimitation(), sampleEntityListPage.getTotal());
        }
    }

    @GetMapping("/api/sample/{id}")
    public SampleModel getSampleById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[getSampleById]-传入参数id为空");
            throw new BizException("传入id不能为空", ReturnCode.INVALID_ID);
        }
        SampleInfo sampleById = sampleService.getSampleById(id);
        return SampleConvertor.data2Model(sampleById);
    }

    @RequestMapping(value = "/api/sample/{id}", method = RequestMethod.DELETE)
    public String deleteSampleById(@PathVariable String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[deleteSampleById]-传入参数id为空");
            throw new BizException("传入id不能为空", ReturnCode.INVALID_ID);
        }

        SampleInfo sampleById = sampleService.getSampleById(id);
        if (sampleById == null) {
            log.info("[deleteSampleById]-在删除过程中查找id={}的样本", id);
            throw new BizException("未找到对应样本", ReturnCode.DATA_NOT_FOUND);
        }

        DataSetInfo dataSetByDataSetId = dataSetService.getDataSetByDataSetId(sampleById.getDatasetId());
        if (dataSetByDataSetId == null) {
            log.info("[deleteSampleById]-在删除过程中查找id={}的数据集", id);
            throw new BizException("未找到样本对应的数据集", ReturnCode.DATA_NOT_FOUND);
        }

        // 校验权限
        String nameInContext = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!ObjectUtils.nullSafeEquals(nameInContext, dataSetByDataSetId.getPublisherName())) {
            log.warn("[deleteSampleById]-没有删除样本集的权限");
            throw new BizException("没有删除样本的权限", ReturnCode.RC403);
        }

        int i = sampleService.deleteSampleById(id);
        if (i == 1) {
            return "删除成功";
        } else {
            return "没有删除到指定数据";
        }
    }
}
