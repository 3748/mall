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
     * 据id查询商品信息(包括商品描述和商品详情)
     *
     * @param id 商品id
     * @return ItemResponse
     */
    ItemResponse selectItemResponseById(Long id);

    /**
     * 据id查询商品信息
     * @param id 商品id
     * @return 商品信息
     */
    Item selectItemById(Long id);

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
