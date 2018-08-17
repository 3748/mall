package com.mall.manage.service;

import java.util.List;

import com.mall.manage.bean.ItemCat;
import com.mall.manage.vo.ItemCatResult;

/**
 * @describe 商品类目
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemCatService {
	/**
	 * 根据父id获取所有的子商品类目
	 * 
	 * @param parentId
	 * @return
	 */
	public List<ItemCat> getListByParentId(int parentId);

	/**
	 * 查询全部商品类目，并且生成树状结构
	 * 
	 * @return
	 */
	public ItemCatResult queryAllToTree();
}
