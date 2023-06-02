package com.zyc.common.entity;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * @author zyc
 * @version 1.0
 */
public class TagEntity {
    String tagId;
    String sampleId;
    String taggerId;
    /**
     * 实际构造的时候可以通过时间毫秒值进行转换
     */
    Timestamp tagTime;
    String beginPos;
    String endPos;
    String tag;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }


    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getTaggerId() {
        return taggerId;
    }

    public void setTaggerId(String taggerId) {
        this.taggerId = taggerId;
    }

    public Timestamp getTagTime() {
        return tagTime;
    }

    public void setTagTime(Timestamp tagTime) {
        this.tagTime = tagTime;
    }

    public String getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(String beginPos) {
        this.beginPos = beginPos;
    }

    public String getEndPos() {
        return endPos;
    }

    public void setEndPos(String endPos) {
        this.endPos = endPos;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
