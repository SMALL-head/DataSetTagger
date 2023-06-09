package com.zyc.common.data;

import java.sql.Timestamp;
import java.util.Map;

/**
 * beginPos之类的变量从entity中String类型变成了Map类型，使之更容易进行操作。
 * Convertor中需要handler从字符串到Map的转化，并且能对非法参数进行拦截
 * @author zyc
 * @version 1.0
 */
public class TagInfo {
    String tagId;
    String sampleId;
    String taggerId;
    String taggerName;
    Timestamp tagTime;
    Map<String, String> beginPos;
    Map<String, String> endPos;
    Map<String, String> tag;

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

    public Map<String, String> getBeginPos() {
        return beginPos;
    }

    public void setBeginPos(Map<String, String> beginPos) {
        this.beginPos = beginPos;
    }

    public Map<String, String> getEndPos() {
        return endPos;
    }

    public void setEndPos(Map<String, String> endPos) {
        this.endPos = endPos;
    }

    public Map<String, String> getTag() {
        return tag;
    }

    public void setTag(Map<String, String> tag) {
        this.tag = tag;
    }

    public String getTaggerName() {
        return taggerName;
    }

    public void setTaggerName(String taggerName) {
        this.taggerName = taggerName;
    }
}
