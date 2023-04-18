package com.zyc.common.entity;

/**
 * @author zyc
 * @version 1.0
 */
public class SampleEntity {
    String sampleId;
    String datasetId;
    String content;
    String sampleType;
    String tagType;

    public SampleEntity() {
    }

    public SampleEntity(String sampleId, String datasetId, String content, String sampleType, String tagType) {
        this.sampleId = sampleId;
        this.datasetId = datasetId;
        this.content = content;
        this.sampleType = sampleType;
        this.tagType = tagType;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSampleType() {
        return sampleType;
    }

    public void setSampleType(String sampleType) {
        this.sampleType = sampleType;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }
}
