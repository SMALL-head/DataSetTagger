package com.zyc.utils.convertor;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.entity.DataSetEntity;
import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;
import com.zyc.common.exception.EnumAcquireException;
import com.zyc.common.model.DataSetModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

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
        dataSetEntity.setPublisherName(dataSetInfo.getPublisherName());
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
        dataSetModel.setPublisher_name(dataSetInfo.getPublisherName());
        dataSetModel.setName(dataSetInfo.getName());
        return dataSetModel;
    }

    public static DataSetInfo dataSetModel2Info(DataSetModel dataSetModel) throws EnumAcquireException {
        DataSetInfo dataSetInfo = new DataSetInfo();
        dataSetInfo.setDatasetId(dataSetModel.get_id());
//        dataSetInfo.setName(dataSetModel.get);
        dataSetInfo.setDesc(dataSetModel.getDesc());
        String publisherId1 = dataSetModel.getPublisher_id();
        try {
            int publisherId = Integer.parseInt(publisherId1);
            dataSetInfo.setPublisherId(publisherId);
        } catch (NumberFormatException ignored) {
            // 如果传入参数有异常，则打印警告日志，其他什么都不用管
            log.warn("[dataSetModel2Info]-用户名{}发出了错误的publisherId={}，其不是数字类型", SecurityContextHolder.getContext().getAuthentication().getName(), publisherId1);
        }
        dataSetInfo.setTagType(TagTypeEnum.getEnumByName(dataSetModel.getTag_type()));
        dataSetInfo.setSampleType(SampleTypeEnum.getEnumByName(dataSetModel.getSample_type()));
        return  dataSetInfo;
    }
}
