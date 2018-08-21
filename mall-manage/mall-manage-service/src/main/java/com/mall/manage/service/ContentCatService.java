package com.mall.manage.service;

import java.util.List;

import com.mall.manage.bean.ContentCat;

/**
 * 
 * @author gp6
 *
 * @data 2018年8月20日
 */
public interface ContentCatService {
	/**
	 * 根据父节点id查询分类列表
	 * @param parentId
	 * @return
	 */
	public List<ContentCat> queryListByParentId(Long parentId);
}
