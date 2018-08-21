package com.mall.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.bean.ContentCat;
import com.mall.manage.mapper.ContentCatMapper;
import com.mall.manage.service.ContentCatService;

/**
 * @author gp6
 *
 * @data 2018年8月20日
 */
@Service
public class ContentCatServiceImpl implements ContentCatService {
	@Autowired
	private ContentCatMapper contentCatMapper;

	@Override
	public List<ContentCat> queryListByParentId(Long parentId) {
		ContentCat contentCat = new ContentCat();
		contentCat.setParentId(parentId);
		return contentCatMapper.select(contentCat);
	}
}
