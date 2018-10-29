package com.mall.manage.controller.api;

import com.mall.common.bean.Item;
import com.mall.common.response.ItemResponse;
import com.mall.manage.service.ItemService;
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
 * 商品接口(供商城前台调用)
 *
 * @author gp6
 * @date 2018-08-16
 */
@Controller
@RequestMapping({"api/item"})
public class ApiItemController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiItemController.class);

    @Autowired
    private ItemService itemService;

    /**
     * 根据id查询商品信息(包括商品描述和商品详情)
     *
     * @param id 商品id
     * @return ResponseEntity<Item>
     */
    @RequestMapping(value = "response/{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemResponse> selectItemResponseById(@PathVariable("id") Long id) {
        ItemResponse itemResponse = itemService.selectItemResponseById(id);
        try {
            if (null == itemResponse) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(itemResponse);
        } catch (Exception e) {
            LOGGER.error("根据id查询商品信息(包括商品描述和商品详情)失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 根据id查询商品信息
     *
     * @param id 商品id
     * @return ResponseEntity<Item>
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Item> selectItemById(@PathVariable("id") Long id) {
        Item item = itemService.selectItemById(id);

        try {
            if (null == item) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(item);
        } catch (Exception e) {
            LOGGER.error("根据id查询商品信息失败,原因:" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
