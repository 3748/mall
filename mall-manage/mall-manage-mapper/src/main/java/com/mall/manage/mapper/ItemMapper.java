package com.mall.manage.mapper;

import com.github.abel533.mapper.Mapper;
import com.mall.manage.bean.Item;
import com.mall.manage.vo.ItemVo;

/**
 * @author gp6
 *
 * @data 2018年8月20日
 */
public interface ItemMapper extends Mapper<Item> {
	/**
	 * 根据Id获取商品信息
	 * 
	 * @return
	 */
	public ItemVo getItemInfoById(Long id);
}