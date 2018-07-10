package com.mall.manage.model;

import java.util.List;
import java.util.Map;

/**
 * 新增商品时接收前台参数
 * 
 * @author gp6
 * @date 2018-07-09
 */
public class ItemModel {

	/**
	 * 商品标题
	 */
	private String title;

	/**
	 * 商品卖点
	 */
	private String sellPoint;

	/**
	 * 商品价格，单位为：分
	 */
	private Long price;

	/**
	 * 库存数量
	 */
	private Integer num;

	/**
	 * 商品条形码
	 */
	private String barcode;

	/**
	 * 商品图片
	 */
	private String image;

	/**
	 * 所属类目，叶子类目
	 */
	private Integer cid;

	/**
	 * 商品描述
	 */
	private String itemDesc;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 商品规格参数
	 */
	private List<Map<String, Object>> itemParams;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSellPoint() {
		return sellPoint;
	}

	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public String getItemDesc() {
		return itemDesc;
	}

	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public List<Map<String, Object>> getItemParams() {
		return itemParams;
	}

	public void setItemParams(List<Map<String, Object>> itemParams) {
		this.itemParams = itemParams;
	}
}
