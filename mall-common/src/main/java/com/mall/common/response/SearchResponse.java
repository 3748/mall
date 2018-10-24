package com.mall.common.response;

import java.util.List;

/**
 * 搜索结果
 *
 * @author gp6
 * @date 2018-10-24
 */
public class SearchResponse {
    private Long total;
    private List<?> list;

    public SearchResponse(Long total, List<?> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<?> getList() {
        return this.list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }
}
