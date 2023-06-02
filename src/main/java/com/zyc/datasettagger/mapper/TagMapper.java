package com.zyc.datasettagger.mapper;

import com.zyc.common.entity.TagEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@Mapper
public interface TagMapper {
    int addTag(TagEntity tagEntity);

    List<TagEntity> getTagByLimitation(String datasetId, int offset, int limitation);

    int countAll();
    int countAllByDatasetId(String datasetId);

    int deleteTagById(String id);
}
