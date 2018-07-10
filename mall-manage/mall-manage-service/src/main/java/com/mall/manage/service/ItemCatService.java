package com.mall.manage.service;

import java.util.List;

import com.mall.manage.bean.ItemCat;

/**
 * @describe 商品类目
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemCatService {
	/**
	 * 根据父id获取所有的子商品类目
	 * @param parentId
	 * @return
	 */
	public List<ItemCat> getListByParentId(int parentId);
}
