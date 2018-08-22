package com.mall.manage.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.constant.Constants;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.utils.RedisUtil;
import com.mall.manage.bean.Item;
import com.mall.manage.bean.ItemDesc;
import com.mall.manage.bean.ItemParam;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.model.ItemModel;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemParamService;
import com.mall.manage.service.ItemService;
import com.mall.manage.vo.ItemVo;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemServiceImpl implements ItemService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	private static final ObjectMapper OBJECTMAPPER = new ObjectMapper();

	/**
	 * 定义key的规则:项目名_模块名_业务名
	 */
	private static final String REDIS_KEY = "MANAGE_MALL_ITEM_DETAIL";
	
	private static final Integer REDIS_TIME = 60 * 60 * 24;
	
	@Autowired
	private RedisUtil redisUtil;

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
	public ItemVo getItemInfoById(Long id) {
		
		String key = REDIS_KEY + id;
		
		try {
			// 从缓存中命中
			String cacheData = redisUtil.get(key);
			if (StringUtils.isNotEmpty(cacheData)) {
				return OBJECTMAPPER.readValue(cacheData, ItemVo.class);
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		
		ItemVo itemVo = itemMapper.getItemInfoById(id);
		try {
			if (null == itemVo) {
				return null;
			}
			
			// 将数据写入到缓存中
			redisUtil.set(key, OBJECTMAPPER.writeValueAsString(itemVo), REDIS_TIME);
			return itemVo;
		} catch (JsonProcessingException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
