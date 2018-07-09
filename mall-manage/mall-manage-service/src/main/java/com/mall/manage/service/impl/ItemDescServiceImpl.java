package com.mall.manage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.bean.ItemDesc;
import com.mall.manage.mapper.ItemDescMapper;
import com.mall.manage.service.ItemDescService;


@Service
public class ItemDescServiceImpl implements ItemDescService {
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	public Integer saveItemDesc(ItemDesc itemDesc){
		return itemDescMapper.insert(itemDesc);
	}
}
