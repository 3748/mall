package com.mall.manage.bean;

import java.util.Date;

import javax.persistence.Table;

/**
 * 商品描述
 * 
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "tb_item_desc")
public class ItemDesc {
	/**
	 * 商品ID
	 */
	private Long itemId;

	/**
	 * 创建时间
	 */
	private Date created;

	/**
	 * 更新时间
	 */
	private Date updated;

	/**
	 * 商品描述
	 */
	private String itemDesc;

	/**
	 * 商品ID
	 * 
	 * @return item_id 商品ID
	 */
	public Long getItemId() {
		return itemId;
	}

	/**
	 * 商品ID
	 * 
	 * @param itemId
	 *            商品ID
	 */
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	/**
	 * 创建时间
	 * 
	 * @return created 创建时间
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * 创建时间
	 * 
	 * @param created
	 *            创建时间
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

	/**
	 * 更新时间
	 * 
	 * @return updated 更新时间
	 */
	public Date getUpdated() {
		return updated;
	}

	/**
	 * 更新时间
	 * 
	 * @param updated
	 *            更新时间
	 */
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	/**
	 * 商品描述
	 * 
	 * @return item_desc 商品描述
	 */
	public String getItemDesc() {
		return itemDesc;
	}

	/**
	 * 商品描述
	 * 
	 * @param itemDesc
	 *            商品描述
	 */
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc == null ? null : itemDesc.trim();
	}
}