package com.zyc.common.model.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author zyc
 * @version 1.0
 */
public class Pagination {
    private String page_size;

    private String page_num;

    public Pagination() {
    }

    public Pagination(String page_size, String page_num) {
        this.page_size = page_size;
        this.page_num = page_num;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }
}
