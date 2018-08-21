package com.mall.manage.service;

import com.mall.manage.bean.ItemDesc;

/**
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemDescService {

	/**
	 * 保存商品描述
	 * 
	 * @param itemDesc
	 * @return
	 */
	public Integer saveItemDesc(ItemDesc itemDesc);
}
