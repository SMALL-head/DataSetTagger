package com.zyc.datasettagger.service.impl;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.entity.DataSetEntity;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.model.ListPage;
import com.zyc.common.security.entity.User;
import com.zyc.datasettagger.config.security.mapper.UserMapper;
import com.zyc.datasettagger.mapper.DataSetMapper;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.utils.convertor.DataSetConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    UserMapper userMapper;

    @Autowired
    public void setUserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

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
        User userById = userMapper.getUserById(dataSetEntity.getPublisherId());
        DataSetInfo dataSetInfo = DataSetConvertor.DataSetEntity2DataSetInfo(dataSetEntity);
        log.info("[getDataSetByDataSetId]-获取到user信息{}", userById);
        dataSetInfo.setPublisherName(userById.getUsername());
        return dataSetInfo;
    }

    @Override
    public int updateDataset(DataSetInfo dataSetInfo) throws BizException {
        if (dataSetInfo == null) {
            log.warn("[updateDataset]-传入dataSetInfo为null");
            return 0;
        }

        DataSetEntity dataSetEntity = dataSetMapper.selectByDatasetId(dataSetInfo.getDatasetId());
        int publisherId = dataSetEntity.getPublisherId(); // publisherId为数据库中查询的owner
        if (publisherId != dataSetInfo.getPublisherId()) {
            log.warn("[updateDataset]-无权限修改数据集,请求者为{}, 数据集owner为{}", dataSetInfo.getPublisherId(), publisherId);
            throw new BizException("无修改数据集权限", ReturnCode.RC403);
        }
        log.info("[updateDataset]-更新数据集, {}", dataSetInfo.printUpdateInfo());
        return dataSetMapper.updateDataSetInfo(DataSetConvertor.dataSetInfo2Entity(dataSetInfo));
    }

    @Override
    public int deleteDatasetById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.warn("[deleteDatasetById]-非法id");
            return 0;
        }
        return dataSetMapper.deleteDatasetById(id);
    }
}
