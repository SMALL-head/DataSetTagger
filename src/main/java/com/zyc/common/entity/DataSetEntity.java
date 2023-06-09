package com.zyc.common.entity;

import java.sql.Timestamp;

/**
 * @author zyc
 * @version 1.0
 */

public class DataSetEntity {
    int id;
    String datasetId;
    Timestamp pubTime;
    String desc;
    String tagType;
    String sampleType;
    String name;
    int publisherId;
    String publisherName;
    String relation;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getDatasetId() {
        return datasetId;
    }

    public void setDatasetId(String datasetId) {
        this.datasetId = datasetId;
    }

    public DataSetEntity() {
    }

    public DataSetEntity(int id, Timestamp pubTime, String desc, String tagType, String sampleType, String name, int publisherId) {
        this.id = id;
        this.pubTime = pubTime;
        this.desc = desc;
        this.tagType = tagType;
        this.sampleType = sampleType;
        this.name = name;
        this.publisherId = publisherId;
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getDataset_id() {
        return datasetId;
    }

    public void setDataset_id(String datasetId) {
        this.datasetId = datasetId;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString() {
        return "DataSetEntity{" +
            "datasetId='" + datasetId + '\'' +
            ", desc='" + desc + '\'' +
            ", name='" + name + '\'' +
            ", publisherId=" + publisherId +
            ", publisherName='" + publisherName + '\'' +
            ", relation='" + relation + '\'' +
            '}';
    }
}
