package com.mall.manage.service;

import com.mall.manage.bean.ItemParamItem;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemParamItemService {

	/**
	 * 保存商品规格参数
	 * 
	 * @param itemParamItem
	 * @return
	 */
	public int saveItemParamItem(ItemParamItem itemParamItem);
}
