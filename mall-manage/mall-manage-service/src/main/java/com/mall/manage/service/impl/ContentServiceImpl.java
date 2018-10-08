package com.mall.manage.service.impl;

import java.util.List;

import com.mall.common.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Content;
import com.mall.manage.mapper.ContentMapper;
import com.mall.manage.service.ContentService;

/**
 * @author gp6
 * @date 2018年8月21日
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public PageInfo<Content> selectContentListByCatId(Long contentCatId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Content content = new Content();
        content.setContentCatId(contentCatId);
        List<Content> contents = contentMapper.select(content);

        return new PageInfo<>(contents);
    }

    @Override
    public void insertContent(Content content){
        content.setId(null);
        content.setCreateTime(DateTimeUtil.CURRENTTIME);
        content.setUpdateTime(content.getCreateTime());
        contentMapper.insert(content);
    }
}
