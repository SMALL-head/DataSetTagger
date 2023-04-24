package com.zyc.datasettagger.service;

import com.zyc.common.data.SampleInfo;
import com.zyc.common.entity.SampleEntity;
import com.zyc.common.model.ListPage;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
public interface SampleService {
    /**
     * 插入新的样本
     * @return 返回生成的样本id
     */
    String addSample(SampleInfo sampleInfo);

    List<SampleEntity> getSampleByDatasetId(String datasetId);

    ListPage<SampleInfo> getSampleByLimitation(String datasetId, int curPage, int limitation);
    SampleInfo getSampleById(String id);
    int deleteSampleById(String id);
}
