package com.mall.web.controller;

import com.mall.common.bean.Item;
import com.mall.web.service.ItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 商品
 *
 * @author gp6
 * @date 2018-08-21
 */
@Controller
@RequestMapping({"item"})
public class ItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 调用mall.com/item/test/2.html, 会出现406错误,由于浏览器把数据当成html解析导致
     *
     * @param id 商品id
     * @return ResponseEntity<ItemResponse>
     */
    @RequestMapping(value = "test/{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> getItemInfoById(@PathVariable("id") Long id) {
        Item item = itemService.selectItemById(id);
        try {
            if (null == item) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            LOGGER.error("(接口)用Http获取商品信息失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 获取商品详情
     *
     * @param id 商品id
     * @return ResponseEntity<ItemResponse>
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> selectItemById(@PathVariable("id") Long id) {
        Item item = itemService.selectItemById(id);
        try {
            // 未获取到商品信息
            if (null == item) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            LOGGER.error("调用Http获取商品信息失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
