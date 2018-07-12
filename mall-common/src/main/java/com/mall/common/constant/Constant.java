package com.mall.common.constant;


/**
 * 项目中所用常量
 * 
 * @author gp6
 * @date 2018-07-07
 */
public interface Constant {

	/*
	 * 商品状态
	 */
	//正常
	public static final int ITEM_STATUS_NORMAL = 1;
	//下架
	public static final int ITEM_STATUS_STOP = 2;
	//删除
	public static final int ITEM_STATUS_DELETE = 3;
	
	/**
	 * 上传图片时,允许上传的格式
	 */
	public static final String[] IMAGE_TYPE = new String[] { ".bmp", ".jpg", ".jpeg", ".gif", ".png" };
}
