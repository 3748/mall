package com.mall.manage.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.constant.Constants;
import com.mall.common.utils.DateTimeUtil;
import com.mall.manage.bean.Item;
import com.mall.manage.bean.ItemDesc;
import com.mall.manage.bean.ItemParam;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.model.ItemModel;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemParamService;
import com.mall.manage.service.ItemService;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDescService itemDescService;

	@Autowired
	private ItemParamService itemParamService;

	@Autowired
	private ItemMapper itemMapper;

	@Override
	public Boolean saveItem(ItemModel itemModel) {
		itemModel.setCreateTime(DateTimeUtil.getCurrentTime());
		itemModel.setUpdateTime(itemModel.getCreateTime());

		// 保存商品基本信息
		Item item = new Item();
		BeanUtils.copyProperties(itemModel, item);
		item.setStatus(Constants.ITEM_STATUS_NORMAL);
		Integer countItem = itemMapper.insert(item);

		// 保存商品描述
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setItemId(item.getId());
		BeanUtils.copyProperties(itemModel, itemDesc);
		Integer countItemDesc = itemDescService.saveItemDesc(itemDesc);

		// 保存商品规格参数
		ItemParam itemParam = new ItemParam();
		itemParam.setItemId(item.getId());
		itemParam.setParamData(itemModel.getItemParams().toString());
		Integer countItemParam = itemParamService.saveItemParam(itemParam);

		return Boolean.valueOf(
				(countItem.intValue() == 1) && (countItemDesc.intValue() == 1) && (countItemParam.intValue() == 1));
	}
	
	@Override
	public Item getItemInfoById(Long id){
		return itemMapper.selectByPrimaryKey(id);
	}

}
