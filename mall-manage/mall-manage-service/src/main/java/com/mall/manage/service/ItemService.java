package com.mall.manage.service;

import com.mall.common.bean.Item;
import com.mall.common.model.ItemModel;
import com.mall.common.vo.ItemVo;

/**
 * 商品管理
 *
 * @author gp6
 * @date 2018-07-09
 */
public interface ItemService {
    /**
     * 新增商品
     *
     * @param itemModel 前台传入商品参数
     * @return Boolean
     */
    Boolean saveItem(ItemModel itemModel);

    /**
     * 获取商品信息
     *
     * @param id 商品id
     * @return ItemVo
     */
    ItemVo getItemInfoById(Long id);

    /**
     * 更新商品
     *
     * @param itemModel 前台传入商品参数
     * @return Boolean
     */
    Boolean updateItem(ItemModel itemModel);
}
