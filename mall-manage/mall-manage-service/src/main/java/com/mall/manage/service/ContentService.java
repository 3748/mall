package com.mall.manage.service;

import com.github.pagehelper.PageInfo;
import com.mall.manage.bean.Content;

public interface ContentService {
	public PageInfo<Content> queryListByCatId(Long contentCatId, Integer pageNum,Integer pageSize);
}
