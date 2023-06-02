package com.zyc.common.model.page;

import com.zyc.common.model.DataSetModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 后端内ListPage<DatasetInfo>最终将会转化为这个类，并将结果返回给前端
 * @author zyc
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatasetListPage implements Serializable {
    /**
     * 当前页号
     */
    int curPage;
    /**
     * 一共多少页
     */
    int pageSize;
    /**
     * 页内容
     */
    List<DataSetModel> datasets;
    /**
     * 每页的大小
     */
    int limitation;

    int total;
}
