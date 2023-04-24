package com.zyc.utils.convertor;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.data.SampleInfo;
import com.zyc.common.entity.SampleEntity;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.SampleModel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zyc
 * @version 1.0
 */
@Slf4j
public class SampleConvertor {
    public static SampleEntity sample2SampleEntity(SampleInfo sampleInfo) {
        return new SampleEntity(sampleInfo.getSampleId(), sampleInfo.getDatasetId(), sampleInfo.getContent(), sampleInfo.getSampleType().getName(), sampleInfo.getTagType().getName());
    }

    public static SampleInfo model2Info(SampleModel model) throws EnumAcquireException {
        String sampleType = model.getSample_type();
        String tagType = model.getTag_type();
        return new SampleInfo(null, model.getContent(), model.getDataset_id(), SampleTypeEnum.getEnumByName(sampleType), TagTypeEnum.getEnumByName(tagType));
    }

    public static SampleInfo entity2Info(SampleEntity entity) {
        SampleInfo sampleInfo = new SampleInfo();
        sampleInfo.setSampleId(entity.getSampleId());
        try {
            sampleInfo.setSampleType(SampleTypeEnum.getEnumByName(entity.getSampleType()));
            sampleInfo.setTagType(TagTypeEnum.getEnumByName(entity.getTagType()));
        } catch (EnumAcquireException e) {
            log.warn("[entity2Info]-枚举便利那个转化错误, e={}", e.getMessage());
        }
        sampleInfo.setContent(entity.getContent());
        sampleInfo.setDatasetId(entity.getDatasetId());
        return sampleInfo;
    }

    public static SampleModel data2Model(SampleInfo data) {
        SampleModel sampleModel = new SampleModel();
        sampleModel.set_id(data.getSampleId());
        sampleModel.setSample_type(data.getSampleType().getName());
        sampleModel.setTag_type(data.getTagType().getName());
        sampleModel.setContent(data.getContent());
        sampleModel.setDataset_id(data.getDatasetId());
        return sampleModel;
    }
}
