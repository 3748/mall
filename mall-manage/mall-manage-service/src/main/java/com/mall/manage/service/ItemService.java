package com.mall.manage.service;

import com.mall.manage.model.ItemModel;

/**
 * @describe 商品管理
 * 
 * @author gp6
 *
 * @date 2018-07-09
 */
public interface ItemService {
	/**
	 * 新增商品
	 * 
	 * @param itemModel
	 * @return
	 */
	public Boolean saveItem(ItemModel itemModel);
}