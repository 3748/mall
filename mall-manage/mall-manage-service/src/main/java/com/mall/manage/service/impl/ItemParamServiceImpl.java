package com.mall.manage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.utils.DateTimeUtil;
import com.mall.manage.bean.ItemParam;
import com.mall.manage.mapper.ItemParamMapper;
import com.mall.manage.service.ItemParamService;

/**
 * 商品规格参数
 * 
 * @author gp6
 * @date 2018-07-07
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ItemParamServiceImpl.class);

	@Autowired
	private ItemParamMapper itemParamMapper;

	@Override
	public int saveItemParam(ItemParam itemParam) {
		int count = 0;

		try {
			itemParam.setCreateTime(DateTimeUtil.getCurrentTime());
			itemParam.setUpdateTime(itemParam.getCreateTime());
			count = itemParamMapper.insert(itemParam);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return count;
	}
}
