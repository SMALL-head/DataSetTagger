package com.zyc.utils.convertor;

import com.zyc.common.constants.TagConstants;
import com.zyc.common.data.TagInfo;
import com.zyc.common.entity.TagEntity;
import com.zyc.common.exception.BizException;
import com.zyc.common.exception.ConvertException;
import com.zyc.common.model.TagModel;
import com.zyc.common.model.param.TagCreateParam;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author zyc
 * @version 1.0
 */
public class TagConvertor {
    public static TagEntity data2Entity(TagInfo tagInfo) throws ConvertException {
        TagEntity tagEntity = new TagEntity();
        tagEntity.setTagId(tagInfo.getTagId());
        tagEntity.setSampleId(tagInfo.getSampleId());
        tagEntity.setTaggerId(tagInfo.getTaggerId());
        tagEntity.setTagTime(tagInfo.getTagTime());

        List<String> posCollection = List.of(TagConstants.POS_TIME, TagConstants.POS_BCHAR, TagConstants.POS_LOCATION);
        List<String> tagCollection = List.of(TagConstants.TYPE_SCORE, TagConstants.TYPE_CATEGORY, TagConstants.TYPE_TEXT);
        Map<String, String> beginPos = tagInfo.getBeginPos();
        Map<String, String> endPos = tagInfo.getEndPos();
        Map<String, String> tag = tagInfo.getTag();
        if (beginPos.size() != 1) {
            throw new ConvertException("beginPos参数异常，只允许一个类型", TagInfo.class, TagEntity.class);
        }
        for (String key : beginPos.keySet()) {
            if (!posCollection.contains(key)) {
                throw new ConvertException("beginPos参数异常," + key
                    + "不是正确的key类型", TagInfo.class, TagEntity.class);
            }
            tagEntity.setBeginPos(constructKeyValueString(key, beginPos.get(key)));
        }

        if (endPos.size() != 1) {
            throw new ConvertException("endPos参数异常，只允许一个类型", TagInfo.class, TagEntity.class);
        }
        for (String key : endPos.keySet()) {
            if (!posCollection.contains(key)) {
                throw new ConvertException("endPos参数异常," + key
                    + "不是正确的key类型", TagInfo.class, TagEntity.class);
            }
            tagEntity.setEndPos(constructKeyValueString(key, endPos.get(key)));
        }
        if (tag.size() != 1) {
            throw new ConvertException("tag参数异常，只允许一个类型", TagInfo.class, TagEntity.class);
        }
        for (String key : tag.keySet()) {
            if (!tagCollection.contains(key)) {
                throw new ConvertException("tag参数异常," + key
                    + "不是正确的key类型", TagInfo.class, TagEntity.class);
            }
            tagEntity.setTag(constructKeyValueString(key, tag.get(key)));
        }

        return tagEntity;
    }

    public static TagInfo entity2Info(TagEntity tagEntity) {
        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagId(tagEntity.getTagId());
        tagInfo.setSampleId(tagEntity.getSampleId());
        tagInfo.setTagTime(tagEntity.getTagTime());
        tagInfo.setTaggerId(tagEntity.getTaggerId());
        tagInfo.setBeginPos(splitByColon(tagEntity.getBeginPos()));
        tagInfo.setEndPos(splitByColon(tagEntity.getEndPos()));
        tagInfo.setTag(splitByColon(tagEntity.getTag()));
        return tagInfo;
    }

    public static TagInfo param2Info(TagCreateParam param, String taggerId) {
        TagInfo tagInfo = new TagInfo();
        tagInfo.setTagId(UUID.randomUUID().toString());
        tagInfo.setSampleId(param.getSample_id());
        tagInfo.setBeginPos(param.getBegin_pos());
        tagInfo.setEndPos(param.getEnd_pos());
        tagInfo.setTag(param.getTag());
        tagInfo.setTagTime(new Timestamp(System.currentTimeMillis()));
        tagInfo.setTaggerId(taggerId);
        return tagInfo;
    }

    /**
     * return "key : value"
     */
    private static String constructKeyValueString(Object key, Object value) {
        return key + " : " + value;
    }

    /**
     * 根据冒号拆成Map，例如“key1 : value1”变成Map类型的情况
     *
     * @param string 需要转换为字符串
     * @return 见注释
     */
    public static Map<String, String> splitByColon(String string) {
        String[] split = string.split(":");
        Map<String, String> res = new HashMap<>();
        res.put(split[0].trim(), split[1].trim());
        return res;
    }

    public static TagModel createFromInfo(TagInfo tagInfo) {
        TagModel tagModel = new TagModel();
        tagModel.set_id(tagInfo.getTagId());
        tagModel.setSample_id(tagInfo.getSampleId());
        tagModel.setTagger_id(tagInfo.getTaggerId());
        tagModel.setBegin_pos(tagInfo.getBeginPos());
        tagModel.setEnd_pos(tagInfo.getEndPos());
        tagModel.setTag(tagInfo.getTag());
        tagModel.setTag_time(tagInfo.getTagTime().toString());
        return tagModel;
    }
}
