package com.mall.manage.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.mall.manage.bean.Content;

/**
 * @author gp6
 *
 * @data 2018年8月20日
 */
public interface ContentMapper extends Mapper<Content> {
	public List<Content> queryListByCatId(Long contentCatId);
}