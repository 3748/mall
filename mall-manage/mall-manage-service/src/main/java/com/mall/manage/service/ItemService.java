package com.mall.manage.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Item;
import com.mall.common.request.ItemRequest;
import com.mall.common.response.ItemResponse;

/**
 * 商品管理
 *
 * @author gp6
 * @date 2018-07-09
 */
public interface ItemService {
    /**
     * 新增商品
     *
     * @param itemRequest 前台传入商品参数
     * @return Boolean
     */
    Boolean insertItem(ItemRequest itemRequest);

    /**
     * 获取商品信息
     *
     * @param id 商品id
     * @return ItemResponse
     */
    ItemResponse selectItemById(Long id);

    /**
     * 更新商品
     *
     * @param itemRequest 前台传入商品参数
     * @return Boolean
     */
    Boolean updateItem(ItemRequest itemRequest);

    /**
     * 查询商品列表页
     *
     * @param pageNum  第几页
     * @param pageSize 每页显示条数
     * @return ResponseEntity<Item>
     */
    PageInfo<Item> selectItemList(int pageNum, int pageSize);
}
