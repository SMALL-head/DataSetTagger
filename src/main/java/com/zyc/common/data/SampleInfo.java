package com.zyc.common.data;

/**
 * @author zyc
 * @version 1.0
 */
public class SampleInfo {
    String sampleId;

    String content;
    String datasetId;
    String sampleType;
    String tagType;

    public SampleInfo() {
    }

    public SampleInfo(String sampleId, String content, String datasetId, String sampleType, String tagType) {
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

    public String getDataSetId() {
        return datasetId;
    }

    public void setDataSetId(String datasetId) {
        this.datasetId = datasetId;
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
