package com.mall.web.controller;

import com.mall.common.bean.Item;
import com.mall.common.request.OrderRequest;
import com.mall.common.response.MallResponse;
import com.mall.web.service.ItemService;
import com.mall.web.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理
 *
 * @author gp6
 * @date 2018-10-17
 */
@Controller
@RequestMapping({"order"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemService itemService;

    /**
     * 点击购买,返回商品详情
     *
     * @param itemId 商品id
     * @return ResponseEntity
     */
    @RequestMapping(value = {"{itemId}"}, method = RequestMethod.GET)
    public ResponseEntity<Item> toOrder(@PathVariable("itemId") Long itemId) {
        Item item = itemService.selectItemById(itemId);
        return ResponseEntity.ok(item);
    }

    /**
     * 新增订单
     *
     * @param orderRequest 订单信息
     * @return MallResponse 自定义返回
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public MallResponse insertOrderRequest(@RequestBody OrderRequest orderRequest) {
        String orderId = orderService.insertOrderRequest(orderRequest);
        if (StringUtils.isEmpty(orderId)) {
            return MallResponse.build(HttpStatus.BAD_REQUEST.value(), "新增订单失败!");
        } else {
            return MallResponse.ok(orderId);
        }
    }
}
