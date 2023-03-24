package com.zyc.common.entity;

import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;

import java.sql.Timestamp;

/**
 * @author zyc
 * @version 1.0
 */

public class DataSetEntity {
    int id;
    Timestamp pubTime;
    String desc;
    String tagType;
    String sampleType;
    String name;

    public DataSetEntity() {
    }

    public DataSetEntity(int id, Timestamp pubTime, String desc, String tagType, String sampleType, String name) {
        this.id = id;
        this.pubTime = pubTime;
        this.desc = desc;
        this.tagType = tagType;
        this.sampleType = sampleType;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getPubTime() {
        return pubTime;
    }

    public void setPubTime(Timestamp pubTime) {
        this.pubTime = pubTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
