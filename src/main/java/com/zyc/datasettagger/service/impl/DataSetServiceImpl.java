package com.zyc.datasettagger.service.impl;

import com.zyc.common.data.DataSetInfo;
import com.zyc.common.model.ListPage;
import com.zyc.datasettagger.mapper.DataSetMapper;
import com.zyc.datasettagger.service.DataSetService;
import com.zyc.utils.Convertor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Service
public class DataSetServiceImpl implements DataSetService {
    // todo： 尚未测试
    DataSetMapper dataSetMapper;

    @Autowired
    public void setDataSetMapper(DataSetMapper dataSetMapper) {
        this.dataSetMapper = dataSetMapper;
    }

    @Override
    public ListPage<DataSetInfo> getAllDataSetInfoByLimitation(int page, int limitation) {
        List<DataSetInfo> res = dataSetMapper.selectAllWithLimitation(page, limitation)
            .stream().map(Convertor::DataSetEntity2DataSetInfo).toList();

        // 总共多少条记录
        int size = dataSetMapper.selectCountAll();
        int pages = (size % limitation) == 0 ? size / limitation : size / limitation + 1;
        // 如果size=0，则总页数应该为1，而不应该是上面的算出的0
        if (size == 0) {
            pages = 1;
        }
        return new ListPage<>(page, pages, res, limitation);
    }
}
