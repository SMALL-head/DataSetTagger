package com.zyc.common.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author zyc
 * @version 1.0
 */
public class TagModel implements Serializable {
    String _id;
    String sample_id;
    String tagger_id;
    String tag_time;
    Map<String, String> begin_pos;
    Map<String, String> end_pos;
    Map<String, String> tag;
    String tagger_name;

    public String getTagger_name() {
        return tagger_name;
    }

    public void setTagger_name(String tagger_name) {
        this.tagger_name = tagger_name;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getSample_id() {
        return sample_id;
    }

    public void setSample_id(String sample_id) {
        this.sample_id = sample_id;
    }

    public String getTagger_id() {
        return tagger_id;
    }

    public void setTagger_id(String tagger_id) {
        this.tagger_id = tagger_id;
    }

    public String getTag_time() {
        return tag_time;
    }

    public void setTag_time(String tag_time) {
        this.tag_time = tag_time;
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
}
