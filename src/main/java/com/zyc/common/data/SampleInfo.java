package com.zyc.common.data;

import com.zyc.common.enums.SampleTypeEnum;
import com.zyc.common.enums.TagTypeEnum;

/**
 * @author zyc
 * @version 1.0
 */
public class SampleInfo {
    String sampleId;

    String content;
    String datasetId;
    SampleTypeEnum sampleType;
    TagTypeEnum tagType;

    public SampleInfo() {
    }

    public SampleInfo(String sampleId, String content, String datasetId, SampleTypeEnum sampleType, TagTypeEnum tagType) {
        this.sampleId = sampleId;
        this.content = content;
        this.datasetId = datasetId;
        this.sampleType = sampleType;
        this.tagType = tagType;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public SampleTypeEnum getSampleType() {
        return sampleType;
    }

    public void setSampleType(SampleTypeEnum sampleType) {
        this.sampleType = sampleType;
    }

    public TagTypeEnum getTagType() {
        return tagType;
    }

    public void setTagType(TagTypeEnum tagType) {
        this.tagType = tagType;
    }
}
