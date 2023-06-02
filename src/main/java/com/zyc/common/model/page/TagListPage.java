package com.zyc.common.model.page;

import com.zyc.common.model.TagModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TagListPage implements Serializable {
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
    List<TagModel> tags;
    /**
     * 每页的大小
     */
    int limitation;

    int total;
}
