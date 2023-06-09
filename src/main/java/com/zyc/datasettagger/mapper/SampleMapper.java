package com.zyc.datasettagger.mapper;

import com.zyc.common.entity.SampleEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Mapper
public interface SampleMapper {
    SampleEntity getSampleById(String id);

    int addSample(SampleEntity sampleEntity);

    List<SampleEntity> getSampleByDatasetId(String datasetId);
    List<SampleEntity> getSampleByLimitation(String datasetId, int cur, int limitation);
    int countAll();
    int deleteById(String id);
    int countSampleByDatasetId(String datasetId);
}
