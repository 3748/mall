package com.mall.manage.service;

import java.util.List;

import com.mall.common.bean.ContentCat;

/**
 * @author gp6
 * @date 2018/8/30
 */
public interface ContentCatService {
    /**
     * 根据父内容类目id查询内容分类列表
     *
     * @param parentId 父内容类目id
     * @return List<ContentCat>
     */
    List<ContentCat> selectContentCatByParentId(Long parentId);

    /**
     * 新增内容类目
     *
     * @param contentCat 内容类目信息
     */
    void insertContentCat(ContentCat contentCat);

    /**
     * 删除内容类目
     *
     * @param id 类目id
     */
    void deleteContentCat(Long id);
}
