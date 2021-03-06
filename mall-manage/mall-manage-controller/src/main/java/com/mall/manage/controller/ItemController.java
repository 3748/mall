package com.mall.manage.controller;

import com.github.pagehelper.PageInfo;
import com.mall.common.bean.Item;
import com.mall.common.request.ItemRequest;
import com.mall.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 商品管理
 *
 * @author gp6
 * @date 2018-07-09
 */
@Controller
@RequestMapping({"item"})
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 新增商品
     *
     * @param itemRequest 前台传入商品参数
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insertItem(@RequestBody ItemRequest itemRequest) {

        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增商品， ItemRequest = {}", itemRequest);
            }

            // 模拟400情况
            if (StringUtils.isEmpty(itemRequest.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean bool = itemService.insertItem(itemRequest);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("新增商品失败， ItemRequest = {}", itemRequest);
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("新增商品成功， ItemRequest = {}", itemRequest);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOGGER.error("新增商品出错! ItemRequest = " + itemRequest, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 更新商品
     *
     * @param itemRequest 前台传入商品参数
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> updateItem(@RequestBody ItemRequest itemRequest) {
        try {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("编辑商品， ItemRequest = {}", itemRequest);
            }
            if (StringUtils.isEmpty(itemRequest.getTitle())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }

            Boolean bool = itemService.updateItem(itemRequest);
            if (!bool) {
                if (LOGGER.isInfoEnabled()) {
                    LOGGER.info("编辑商品失败， ItemRequest = {}", itemRequest);
                }

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
            if (LOGGER.isInfoEnabled()) {
                LOGGER.info("编辑商品成功， itemId = {}", itemRequest.getId());
            }

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            LOGGER.error("编辑商品出错! ItemRequest = " + itemRequest, e);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 查询商品列表页
     *
     * @param pageNum  第几页
     * @param pageSize 每页显示条数
     * @return ResponseEntity<Item>
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageInfo<Item>> selectItemList(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        try {
            PageInfo<Item> pageInfo = itemService.selectItemList(pageNum, pageSize);
            return ResponseEntity.ok(pageInfo);
        } catch (Exception e) {
            LOGGER.error("查询商品列表页" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
