package com.mall.common.response;

import java.util.List;

public class PageResponse<T> {
    private Integer totle;

    private List<T> data;

    public PageResponse() {
    }

    public PageResponse(Integer totle, List<T> data) {
        this.totle = totle;
        this.data = data;
    }

    public Integer getTotle() {
        return totle;
    }

    public void setTotle(Integer totle) {
        this.totle = totle;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
