package com.zyc.datasettagger.service;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.exception.BizException;
import com.zyc.common.model.ListPage;
import jakarta.annotation.Nullable;

/**
 * @author zyc
 * @version 1.0
 */
public interface DataSetService {
    /**
     *
     * @param page 当前页
     * @param limitation 每页的个数
     * @param publisherId 发布者id，可以传入null
     * @return 返回全量查询的结果
     */
    ListPage<DataSetInfo> getAllDataSetInfoByLimitation(int page, int limitation, @Nullable Integer publisherId);

    int insertDataSet(DataSetInfo dataSetInfo);

    DataSetInfo getDataSetByDataSetId(String dataSetId);

    /**
     * 更新数据集信息
     * @param dataSetInfo 修改信息
     * @return 受影响的行数。由于此方法特殊性，若收到修改影响，则返回1；否则返回0
     * @throws BizException 一些可能的异常，比如权限校验之类的
     */
    int updateDataset(DataSetInfo dataSetInfo) throws BizException;

    int deleteDatasetById(String id);
}
