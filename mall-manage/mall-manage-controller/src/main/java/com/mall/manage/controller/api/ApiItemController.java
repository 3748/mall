package com.mall.manage.controller.api;

import com.mall.common.utils.BeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.manage.service.ItemService;
import com.mall.common.response.ItemResponse;

/**
 * 商品接口(供商城前台调用)
 *
 * @author gp6
 * @date 2018-08-16
 */
@Controller
@RequestMapping({"api/item"})
public class ApiItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 根据id查询商品信息
     *
     * @param id 商品id
     * @return ResponseEntity<Item>
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ItemResponse> selectItemById(@PathVariable("id") Long id) {
        ItemResponse itemResponse = itemService.selectItemById(id);

        BeanUtil<ItemResponse> beanUtil = new BeanUtil<>();
        return beanUtil.isNull(itemResponse);
    }
}
