package com.mall.common.response;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-10
 */
public class ItemCatResponse {
    /**
     * 指定序列化json后的名称
     */
    @JsonProperty("data")
    private List<ItemCatData> itemCats = new ArrayList<>();

    public List<ItemCatData> getItemCats() {
        return itemCats;
    }

    public void setItemCats(List<ItemCatData> itemCats) {
        this.itemCats = itemCats;
    }
}
