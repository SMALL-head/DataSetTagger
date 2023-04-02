package com.zyc.datasettagger.service.impl;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.entity.DataSetEntity;
import com.zyc.common.model.ListPage;
import com.zyc.datasettagger.mapper.DataSetMapper;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.datasettagger.service.UserService;
import com.zyc.utils.Convertor;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * DataSetService实现类，为controller提供相应的服务
 * @author zyc
 * @version 1.0
 */
@Service
@Slf4j
public class DataSetServiceImpl implements DataSetService {
    // todo： 尚未测试
    DataSetMapper dataSetMapper;
    @Autowired
    public void setDataSetMapper(DataSetMapper dataSetMapper) {
        this.dataSetMapper = dataSetMapper;
    }

    @Override
    public ListPage<DataSetInfo> getAllDataSetInfoByLimitation(int page, int limitation, Integer publisherId) {
        log.info("[getAllDataSetInfoByLimitation]-全量查询数据库, page={}, limitation={}, publisherId={}", page, limitation, publisherId);
        List<DataSetEntity> dataSetEntityList = dataSetMapper.selectAllWithLimitation((page-1) * limitation, limitation, publisherId);
        log.info("[dataSetEntityList]-全量查询结果：{}", dataSetEntityList.toString());
        List<DataSetInfo> res = dataSetEntityList.stream().map(DataSetConvertor::DataSetEntity2DataSetInfo).toList();

        // 总共多少条记录
        int size = dataSetMapper.selectCountAll();
        int raw_pages = size / limitation;
        int pages = (size % limitation) == 0 ? raw_pages : raw_pages + 1;
        // 如果size=0，则总页数应该为1，而不应该是上面的算出的0
        if (size == 0) {
            pages = 1;
        }
        return new ListPage<>(page, pages, res, limitation);
    }

    @Override
    public int insertDataSet(DataSetInfo dataSetInfo) {
        int i = dataSetMapper.insertDataSetEntity(DataSetConvertor.dataSetInfo2Entity(dataSetInfo));
        if (i == 0) {
            log.warn("[insertDataSet]-向数据库中添加{}条记录，但本应该为1", i);
        } else {
            log.info("[insertDataSet]-向数据集中添加{}条记录", i);
        }
        return i;
    }

    @Override
    public DataSetInfo getDataSetByDataSetId(String dataSetId) {
        DataSetEntity dataSetEntity = dataSetMapper.selectByDatasetId(dataSetId);
        if (dataSetEntity == null) {
            log.warn("[getDataSetByDataSetId]-未找到dataSetId={}的数据集", dataSetId);
            return null;
        }
        return DataSetConvertor.DataSetEntity2DataSetInfo(dataSetEntity);
    }
}
