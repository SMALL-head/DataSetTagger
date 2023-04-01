package com.zyc.datasettagger.service;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.model.ListPage;

/**
 * @author zyc
 * @version 1.0
 */
public interface DataSetService {
    ListPage<DataSetInfo> getAllDataSetInfoByLimitation(int page, int limitation);

    int insertDataSet(DataSetInfo dataSetInfo);

    DataSetInfo getDataSetByDataSetId(String dataSetId);
}
