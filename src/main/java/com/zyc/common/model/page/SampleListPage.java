package com.zyc.common.model.page;

import com.zyc.common.model.SampleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SampleListPage implements Serializable {
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
    List<SampleModel> samples;
    /**
     * 每页的大小
     */
    int limitation;
    /**
     * 一共有多少个
     */
    int total;
}
