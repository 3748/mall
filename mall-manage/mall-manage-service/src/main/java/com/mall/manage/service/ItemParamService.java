package com.mall.manage.service;

import com.mall.common.bean.ItemParam;

/**
 * 商品规格参数
 *
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemParamService {

    /**
     * 保存商品规格参数
     *
     * @param itemParam 商品规格参数
     * @return int
     */
    int saveItemParam(ItemParam itemParam);

    /**
     * 修改商品规格参数
     *
     * @param itemParam 商品规格参数
     * @return int
     */
    int updateItemParam(ItemParam itemParam);

}
