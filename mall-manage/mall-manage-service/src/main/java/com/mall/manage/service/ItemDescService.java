package com.mall.manage.service;

import com.mall.common.bean.ItemDesc;

/**
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemDescService {

    /**
     * 保存商品描述
     *
     * @param itemDesc 商品描述
     * @return int
     */
    int saveItemDesc(ItemDesc itemDesc);

    /**
     * 修改商品描述
     *
     * @param itemDesc 商品描述
     * @return int
     */
    int updateItemDesc(ItemDesc itemDesc);
}
