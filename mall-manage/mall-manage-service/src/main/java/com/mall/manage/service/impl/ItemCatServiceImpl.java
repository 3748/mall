package com.mall.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.bean.ItemCat;
import com.mall.manage.mapper.ItemCatMapper;
import com.mall.manage.service.ItemCatService;

/**
 * @describe 商品类目
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<ItemCat> getListByParentId(int parentId) {
		ItemCat itemCat = new ItemCat();
		itemCat.setParentId(parentId);
		return itemCatMapper.select(itemCat);
	}

}
