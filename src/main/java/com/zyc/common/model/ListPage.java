package com.zyc.common.model;

import lombok.AllArgsConstructor;

import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@AllArgsConstructor
public class ListPage<T> {
    int curPage;
    int pageSize;
    List<T> pageContent;
    int limitation;

    public int getPageSize() {
        return pageSize;
    }

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getPageContent() {
        return pageContent;
    }

    public void setPageContent(List<T> pageContent) {
        this.pageContent = pageContent;
    }

    public int getLimitation() {
        return limitation;
    }

    public void setLimitation(int limitation) {
        this.limitation = limitation;
    }

    @Override
    public String toString() {
        return "ListPage{" +
            "curPage=" + curPage +
            ", pageSize=" + pageSize +
            ", pageContent=" + pageContent +
            '}';
    }
}
