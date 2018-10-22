package com.mall.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.mall.common.bean.Item;
import com.mall.common.response.ItemResponse;

/**
 * @author gp6
 * @date 2018/8/30
 */
public interface ItemMapper extends Mapper<Item> {
    /**
     * 根据Id获取商品信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    ItemResponse getItemInfoById(Long id);
}