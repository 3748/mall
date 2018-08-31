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
	 * @param itemDesc
	 * @return
	 */
	int saveItemDesc(ItemDesc itemDesc);
}
