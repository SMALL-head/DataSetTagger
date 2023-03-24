package com.zyc.common.data;

import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;

import java.sql.Timestamp;

/**
 * 程序内部使用的DataSet类
 * @author zyc
 * @version 1.0
 */
public class DataSetInfo {
    int id;
    Timestamp pubTime;
    String desc;
    TagTypeEnum tagType;
    SampleTypeEnum sampleType;

    String name;

    public DataSetInfo() {
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

    public TagTypeEnum getTagType() {
        return tagType;
    }

    public void setTagType(TagTypeEnum tagType) {
        this.tagType = tagType;
    }

    public SampleTypeEnum getSampleType() {
        return sampleType;
    }

    public void setSampleType(SampleTypeEnum sampleType) {
        this.sampleType = sampleType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DataSetInfo(int id, Timestamp pubTime, String desc, TagTypeEnum tagType, SampleTypeEnum sampleType, String name) {
        this.id = id;
        this.pubTime = pubTime;
        this.desc = desc;
        this.tagType = tagType;
        this.sampleType = sampleType;
        this.name = name;
    }

    @Override
    public String toString() {
        return "DataSetInfo{" +
            "id=" + id +
            ", pubTime=" + pubTime +
            ", desc='" + desc + '\'' +
            ", tagType=" + tagType +
            ", sampleType=" + sampleType +
            ", name='" + name + '\'' +
            '}';
    }
}
