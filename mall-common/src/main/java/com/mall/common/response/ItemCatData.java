package com.mall.common.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品类目数据
 *
 * @author gp6
 * @date 2018-07-10
 */
public class ItemCatData {
    /**
     * 序列化成json数据时为 u
     */
    @JsonProperty("u")
    private String url;

    @JsonProperty("n")
    private String name;

    @JsonProperty("i")
    private List<?> items;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getItems() {
        return items;
    }

    public void setItems(List<?> items) {
        this.items = items;
    }
}
