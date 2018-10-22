package com.mall.manage.service;

import java.util.List;

import com.mall.common.bean.ItemCat;
import com.mall.common.response.ItemCatResponse;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-07
 */
public interface ItemCatService {
    /**
     * 根据父id获取所有的子商品类目
     *
     * @param parentId 商品类目父id
     * @return List<ItemCat>
     */
    List<ItemCat> selectItemCatListByParentId(long parentId);

    /**
     * 查询全部商品类目，并且生成树状结构
     *
     * @return ItemCatResponse
     */
    ItemCatResponse selectAllItemCatListToTree();
}
