package com.mall.web.controller;

import com.mall.common.bean.Item;
import com.mall.common.utils.BeanUtil;
import com.mall.common.response.ItemResponse;
import com.mall.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * 商品
 *
 * @author gp6
 * @date 2018-08-21
 */
@Controller
@RequestMapping({"item"})
public class ItemController {

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

        BeanUtil<Item> beanUtil = new BeanUtil<>();
        return beanUtil.isNull(item);
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

        BeanUtil<Item> beanUtil = new BeanUtil<>();
        return beanUtil.isNull(item);
    }

}
