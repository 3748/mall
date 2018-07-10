package com.mall.manage.bean;

import javax.persistence.Table;

/**
 * 商品描述
 * 
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "m_item_desc")
public class ItemDesc {
	/**
	 * 商品ID
	 */
	private Long itemId;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

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
	 * @return create_time 创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 更新时间
	 * 
	 * @return update_time 更新时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * 更新时间
	 * 
	 * @param updateTime
	 *            更新时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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