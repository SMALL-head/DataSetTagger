package com.zyc.common.model.page;

import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author zyc
 * @version 1.0
 */
@AllArgsConstructor
public class ListPage<T> implements Serializable {
    /**
     * 当前页号
     */
    int curPage;
    /**
     * 一共多少页
     */
    int pageSize;
    /**
     * 页内容
     */
    List<T> pageContent;
    /**
     * 每页的大小
     */
    int limitation;
    /**
     * 一共有多少项
     */
    int total;

    /**
     *
     * @param page 当前页
     * @param limitation 页大小
     * @param size 所有条目数量
     * @param pageContent 页内容
     */
    public ListPage(int page, int limitation, int size, List<T> pageContent) {
        int raw_pages = size / limitation;
        int pages = (size % limitation) == 0 ? raw_pages : raw_pages + 1;
        // 如果size=0，则总页数应该为1，而不应该是上面的算出的0
        if (size == 0) {
            pages = 1;
        }
//        return super(page, pages, pageContent, limitation);
        this.curPage = page;
        this.limitation = limitation;
        this.pageContent = pageContent;
        this.pageSize = pages;
        this.total = size;
    }

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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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
