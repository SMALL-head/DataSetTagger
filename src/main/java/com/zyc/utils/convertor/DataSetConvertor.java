package com.zyc.utils.convertor;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.entity.DataSetEntity;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.DataSetModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

/**
 * @author zyc
 * @version 1.0
 */
@Slf4j
public class DataSetConvertor {
    public static DataSetEntity dataSetInfo2Entity(DataSetInfo dataSetInfo) {
        DataSetEntity dataSetEntity = new DataSetEntity();
        BeanUtils.copyProperties(dataSetInfo, dataSetEntity);
        dataSetEntity.setSampleType(dataSetInfo.getSampleType().getName());
        dataSetEntity.setTagType(dataSetInfo.getTagType().getName());
        dataSetEntity.setDataset_id(dataSetInfo.getDatasetId());
        return dataSetEntity;
    }

    public static DataSetInfo DataSetEntity2DataSetInfo(DataSetEntity entity) {
        DataSetInfo res = new DataSetInfo();
        BeanUtils.copyProperties(entity, res);
        try {
            res.setSampleType(SampleTypeEnum.getEnumByName(entity.getSampleType()));
            res.setTagType(TagTypeEnum.getEnumByName(entity.getTagType()));
        } catch (EnumAcquireException e) {
            log.warn("[DataSetEntity2DataSetInfo] - {}", e.getMessage());
        }
        return res;
    }
    public static DataSetModel DataSetInfo2Model(DataSetInfo dataSetInfo) {
        DataSetModel dataSetModel = new DataSetModel();
        dataSetModel.set_id(dataSetInfo.getDatasetId());
        dataSetModel.setDesc(dataSetInfo.getDesc());
        dataSetModel.setPub_time(dataSetInfo.getPubTime().toString());
        dataSetModel.setPublisher_id(dataSetInfo.getPublisherId() + "");
        dataSetModel.setSample_type(dataSetInfo.getSampleType().getName());
        dataSetModel.setTag_type(dataSetInfo.getTagType().getName());
        return dataSetModel;
    }
}
