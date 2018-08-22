package com.mall.manage.vo;

import com.mall.manage.bean.Item;

/**
 * 返回给前台的商品信息
 * 
 * @author gp6
 * @date 2018-08-22
 */
public class ItemVo extends Item {
	/**
	 * 商品描述
	 */
	private String itemDesc;

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
}
