package com.hajres;

import java.util.List;

public class PaginatedResult<T> {
    private List<T> resultList;
    private long pageCount;

    public PaginatedResult() {
    }

    public PaginatedResult(List<T> resultList, Long pageCount) {
        this.resultList = resultList;
        this.pageCount = pageCount;
    }

    public long getPageCount() {
        return pageCount;
    }

    public void setPageCount(long pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }
}
