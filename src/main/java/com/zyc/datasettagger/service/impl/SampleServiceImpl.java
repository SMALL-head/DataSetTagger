package com.zyc.datasettagger.service.impl;

import com.zyc.common.data.SampleInfo;
import com.zyc.common.entity.SampleEntity;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.ListPage;
import com.zyc.datasettagger.mapper.SampleMapper;
import com.zyc.datasettagger.service.SampleService;
import com.zyc.utils.convertor.SampleConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 与样本相关的操作
 *
 * @author zyc
 * @version 1.0
 */
@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
    SampleMapper sampleMapper;

    @Autowired
    public void setSampleMapper(SampleMapper sampleMapper) {
        this.sampleMapper = sampleMapper;
    }

    @Override
    public String addSample(SampleInfo sampleInfo) {
        if (sampleInfo == null) {
            log.warn("[addSample]-sampleInfo参数不能为null");
            return null;
        }
        if (ObjectUtils.isEmpty(sampleInfo.getDatasetId())) {
            log.warn("[addSample]-新增的样本没有对应的dataset_id");
            return null;
        }

        SampleEntity sampleEntity = SampleConvertor.sample2SampleEntity(sampleInfo);
        String sampleId = UUID.randomUUID().toString();
        sampleEntity.setSampleId(sampleId);

        int i = sampleMapper.addSample(sampleEntity);
        String datasetId = sampleInfo.getDatasetId();
        if (i == 1) {
            log.info("[addSample]-样本插入成功，所属数据集id={}", datasetId);
        } else {
            log.info("[addSample]-样本插入失败，所属数据集id={}", datasetId);
        }

        return sampleId;
    }

    @Override
    public List<SampleEntity> getSampleByDatasetId(String datasetId) {
        return null;
    }

    @Override
    public ListPage<SampleInfo> getSampleByLimitation(String datasetId, int curPage, int limitation) {
        if (ObjectUtils.isEmpty(datasetId)) {
            log.warn("[getSampleByLimitation]-datasetId={}, 不能为空", datasetId);
            return new ListPage<>(1,1,new ArrayList<>(), limitation, 0);
        }

        List<SampleEntity> sampleList = sampleMapper.getSampleByLimitation(datasetId, (curPage - 1) * limitation, limitation);
        List<SampleInfo> res = sampleList.stream().map(SampleConvertor::entity2Info).toList();
        int size = sampleMapper.countAll();
        return new ListPage<>(curPage, limitation, size, res);
    }

    @Override
    public SampleInfo getSampleById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[getSampleById]-传入id不能为空");
            throw new BizException("样本id不能为空", ReturnCode.INVALID_INPUT);
        }
        SampleEntity sampleById = sampleMapper.getSampleById(id);
        return SampleConvertor.entity2Info(sampleById);
    }

    @Override
    public int deleteSampleById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[deleteSampleById]-样本id为空");
            throw new BizException("样本id不能为空", ReturnCode.INVALID_INPUT);
        }
        return sampleMapper.deleteById(id);
    }
}
