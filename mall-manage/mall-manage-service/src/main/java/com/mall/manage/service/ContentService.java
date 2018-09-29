package com.mall.manage.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Content;

/**
 * @author gp6
 * @date 2018-07-07
 */
public interface ContentService {
    /**
     * 根据内容类目ID获取所有内容
     *
     * @param contentCatId 首页内容类目id
     * @param pageNum      页码
     * @param pageSize     每页显示条数
     * @return PageInfo<Content>
     */
    PageInfo<Content> selectContentListByCatId(Long contentCatId, Integer pageNum, Integer pageSize);
}
