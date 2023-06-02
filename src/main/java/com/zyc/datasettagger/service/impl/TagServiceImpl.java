package com.zyc.datasettagger.service.impl;

import com.zyc.common.data.TagInfo;
import com.zyc.common.entity.TagEntity;
import com.zyc.common.enums.ReturnCode;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.ConvertException;
import com.zyc.common.model.page.ListPage;
import com.zyc.datasettagger.mapper.TagMapper;
import com.zyc.datasettagger.service.TagService;
import com.zyc.utils.convertor.TagConvertor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Service
@Slf4j
public class TagServiceImpl implements TagService {
    TagMapper tagMapper;

    @Autowired
    public void setTagMapper(TagMapper tagMapper) {
        this.tagMapper = tagMapper;
    }

    @Override
    public int addTag(TagInfo tagInfo) {
        TagEntity tagEntity;
        try {
            tagEntity = TagConvertor.data2Entity(tagInfo);
        } catch (ConvertException convertException) {
            log.error("[addTag]-" + convertException.getMessage());
            throw new BizException("tagInfo传递参数异常", ReturnCode.INVALID_INPUT);
        }
        return tagMapper.addTag(tagEntity);
    }

    @Override
    public ListPage<TagInfo> getTagByPagination(String datasetId, int curPage, int limitation) {
        List<TagInfo> list = tagMapper.getTagByLimitation(datasetId, (curPage - 1) * limitation, limitation).stream().map(TagConvertor::entity2Info).toList();
        int size = tagMapper.countAll();
        return new ListPage<>(curPage, limitation, size, list);
    }

    @Override
    public int countAll() {
        return tagMapper.countAll();
    }

    @Override
    public int countAll(String datasetId) {
        if (ObjectUtils.isEmpty(datasetId)) {
            log.error("[countAll(String)]-传入的datasetId为空");
            throw new BizException("传入的datasetId为空", ReturnCode.INVALID_INPUT);
        }
        return tagMapper.countAllByDatasetId(datasetId);
    }

    @Override
    public int deleteTagById(String id) {
        if (ObjectUtils.isEmpty(id)) {
            log.error("[deleteTagById]-传入的tag Id为空");
            throw new BizException("传入的tag Id为空", ReturnCode.INVALID_INPUT);
        }
        return tagMapper.deleteTagById(id);
    }
}
