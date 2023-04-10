package com.zyc.common.model;

import java.io.Serializable;

/**
 * @author zyc
 * @version 1.0
 */
public class DataSetModel implements Serializable {
    String _id;
    String publisher_id;
    String publisher_name;
    String pub_time;
    String desc;
    String sample_type;
    String tag_type;

    String name;

    public DataSetModel() {
    }

    public DataSetModel(String _id, String publisher_id, String pub_time, String desc, String sample_type, String tag_type) {
        this._id = _id;
        this.publisher_id = publisher_id;
        this.pub_time = pub_time;
        this.desc = desc;
        this.sample_type = sample_type;
        this.tag_type = tag_type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(String publisher_id) {
        this.publisher_id = publisher_id;
    }

    public String getPub_time() {
        return pub_time;
    }

    public void setPub_time(String pub_time) {
        this.pub_time = pub_time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSample_type() {
        return sample_type;
    }

    public void setSample_type(String sample_type) {
        this.sample_type = sample_type;
    }

    public String getTag_type() {
        return tag_type;
    }

    public void setTag_type(String tag_type) {
        this.tag_type = tag_type;
    }

    public String getPublisher_name() {
        return publisher_name;
    }

    public void setPublisher_name(String publisher_name) {
        this.publisher_name = publisher_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
