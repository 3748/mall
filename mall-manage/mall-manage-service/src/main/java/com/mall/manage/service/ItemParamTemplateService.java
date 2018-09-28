package com.mall.manage.service;

import com.mall.common.bean.ItemParamTemplate;

/**
 * 商品规格参数模板
 *
 * @author gp6
 * @date 2018-09-27
 */
public interface ItemParamTemplateService {

    /**
     * 根据商品类目ID查询商品规格参数模板
     *
     * @param catId 商品类目ID
     * @return ItemParamTemplate
     */
    ItemParamTemplate selectItemParamTemplateByItemId(Long catId);

    /**
     * 新增商品规格参数模板
     *
     * @param itemParamTemplate 参数模板数据
     */
    void insertItemParamTemplate(ItemParamTemplate itemParamTemplate);
}
