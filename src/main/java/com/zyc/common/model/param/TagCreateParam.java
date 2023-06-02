package com.zyc.common.model.param;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
public class TagCreateParam implements Serializable {
    String sample_id;
    Map<String, String> begin_pos;
    Map<String, String> end_pos;
    Map<String, String> tag;

    public TagCreateParam() {
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public Map<String, String> getBegin_pos() {
        return begin_pos;
    }

    public void setBegin_pos(Map<String, String> begin_pos) {
        this.begin_pos = begin_pos;
    }

    public Map<String, String> getEnd_pos() {
        return end_pos;
    }

    public void setEnd_pos(Map<String, String> end_pos) {
        this.end_pos = end_pos;
    }

    public Map<String, String> getTag() {
        return tag;
    }

    public void setTag(Map<String, String> tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "TagCreateParam{" +
            "sample_id='" + sample_id + '\'' +
            ", begin_pos=" + begin_pos +
            ", end_pos=" + end_pos +
            ", tag=" + tag +
            '}';
    }
}
