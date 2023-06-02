package com.zyc.datasettagger.service;

import com.zyc.common.data.TagInfo;
import com.zyc.common.model.page.ListPage;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
public interface TagService {
    int addTag(TagInfo tagInfo);

    ListPage<TagInfo> getTagByPagination(String datasetId, int curPage, int limitation);

    int countAll();

    int countAll(String datasetId);

    int deleteTagById(String id);
}
