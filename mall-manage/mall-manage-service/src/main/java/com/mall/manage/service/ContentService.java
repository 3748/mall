package com.mall.manage.service;

import com.github.pagehelper.PageInfo;
import com.mall.manage.bean.Content;

/**
 * @author gp6
 * @date 2018-07-07
 */
public interface ContentService {
    /**
     * 根据内容类目ID获取所有内容
     *
     * @param contentCatId
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Content> queryListByCatId(Long contentCatId, Integer pageNum, Integer pageSize);
}
