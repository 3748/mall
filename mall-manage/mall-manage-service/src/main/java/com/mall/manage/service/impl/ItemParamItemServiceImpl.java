package com.mall.manage.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.bean.ItemParamItem;
import com.mall.manage.mapper.ItemParamItemMapper;
import com.mall.manage.service.ItemParamItemService;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemParamItemServiceImpl.class);

	@Autowired
	private ItemParamItemMapper itemParamItemMapper;

	@Override
	public int saveItemParamItem(ItemParamItem itemParamItem) {
		int count = 0;

		try {
			itemParamItem.setCreated(new Date());
			itemParamItem.setUpdated(itemParamItem.getCreated());
			count = itemParamItemMapper.insert(itemParamItem);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}
}
