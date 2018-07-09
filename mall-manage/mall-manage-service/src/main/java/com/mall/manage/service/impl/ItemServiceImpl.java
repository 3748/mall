package com.mall.manage.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.mall.manage.bean.Item;
import com.mall.manage.bean.ItemDesc;
import com.mall.manage.bean.ItemParamItem;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.model.ItemModel;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemParamItemService;
import com.mall.manage.service.ItemService;
import com.mall.common.constant.Constant;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemDescService itemDescService;
	
	@Autowired
	private ItemParamItemService itemParamItemService;
	
	@Autowired
	private ItemMapper itemMapper;

	@Override
	public Boolean saveItem(ItemModel itemModel) {
		itemModel.setCreated(new Date());
		itemModel.setUpdated(itemModel.getCreated());
		
		Item item = new Item();
		BeanUtils.copyProperties(itemModel, item);
		item.setStatus(Constant.ITEM_STATUS_NORMAL);
		Integer count1 = itemMapper.insert(item);

		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		BeanUtils.copyProperties(itemModel, itemDesc);
		Integer count2 = itemDescService.saveItemDesc(itemDesc);

		ItemParamItem itemParamItem = new ItemParamItem();
		itemParamItem.setItemId(item.getId());
		itemParamItem.setParamData(itemModel.getItemParams().toString());
		Integer count3 = itemParamItemService.saveItemParamItem(itemParamItem);

		//sendMsg(item.getId(), "insert");

		return Boolean.valueOf(
			(count1.intValue() == 1)
			&& (count2.intValue() == 1) 
			&& (count3.intValue() == 1)
		);
	}

}
