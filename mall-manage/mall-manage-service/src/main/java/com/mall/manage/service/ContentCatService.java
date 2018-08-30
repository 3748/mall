package com.mall.manage.service;

import java.util.List;

import com.mall.manage.bean.ContentCat;

/**
 * @author gp6
 * @date 2018/8/30
 */
public interface ContentCatService {
    /**
     * 根据父节点id查询分类列表
     *
     * @param parentId
     * @return
     */
    List<ContentCat> queryListByParentId(Long parentId);
}
