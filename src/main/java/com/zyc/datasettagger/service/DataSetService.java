package com.zyc.datasettagger.service;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.model.ListPage;
import jakarta.annotation.Nullable;

/**
 * @author zyc
 * @version 1.0
 */
public interface DataSetService {
    /**
     *
     * @param page
     * @param limitation
     * @param publisherId 发布者id，可以传入null
     * @return 返回全量查询的结果
     */
    ListPage<DataSetInfo> getAllDataSetInfoByLimitation(int page, int limitation, @Nullable Integer publisherId);

    int insertDataSet(DataSetInfo dataSetInfo);

    DataSetInfo getDataSetByDataSetId(String dataSetId);
}
