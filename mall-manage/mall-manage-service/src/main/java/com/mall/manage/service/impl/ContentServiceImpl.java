package com.mall.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.manage.bean.Content;
import com.mall.manage.mapper.ContentMapper;
import com.mall.manage.service.ContentService;

/**
 * @author gp6
 *
 * @data 2018年8月21日
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private ContentMapper contentMapper;

	@Override
	public PageInfo<Content> queryListByCatId(Long contentCatId, Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum.intValue(), pageSize.intValue());

		Content content = new Content();
		content.setContentCatId(contentCatId);
		List<Content> contents = contentMapper.select(content);

		PageInfo<Content> pageInfo = new PageInfo<Content>(contents);
		return pageInfo;
	}
}
