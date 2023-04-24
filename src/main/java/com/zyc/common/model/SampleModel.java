package com.zyc.common.model;

import java.io.Serializable;

/**
 * 样本的前端模型
 * @author zyc
 * @version 1.0
 */
public class SampleModel implements Serializable {
    String _id;
    String dataset_id;
    String content;
    String sample_type;
    String tag_type;

    public SampleModel() {
    }

    public SampleModel(String _id, String dataset_id, String content, String sample_type, String tag_type) {
        this._id = _id;
        this.dataset_id = dataset_id;
        this.content = content;
        this.sample_type = sample_type;
        this.tag_type = tag_type;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getDataset_id() {
        return dataset_id;
    }

    public void setDataset_id(String dataset_id) {
        this.dataset_id = dataset_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
}
