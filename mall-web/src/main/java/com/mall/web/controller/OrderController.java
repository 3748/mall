package com.mall.web.controller;

import com.mall.common.bean.Item;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.enums.ResponseMsgEnum;
import com.mall.common.model.OrderModel;
import com.mall.common.vo.MallResult;
import com.mall.web.service.ItemService;
import com.mall.web.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
     * 点击购买,跳转订单确认页
     *
     * @param itemId 商品id
     * @return ModelAndView
     */
    @RequestMapping(value = {"{itemId}"}, method = RequestMethod.GET)
    public ModelAndView toOrder(@PathVariable("itemId") Long itemId) {
        ModelAndView mv = new ModelAndView("order");
        Item item = itemService.selectItemById(itemId);
        mv.addObject("item", item);
        return mv;
    }

    /**
     * 新增订单
     *
     * @param orderModel 订单信息
     * @return MallResult 自定义返回
     */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    @ResponseBody
    public MallResult insertOrderModel(@RequestBody OrderModel orderModel) {

        String orderId = orderService.insertOrderModel(orderModel);
        if (StringUtils.isEmpty(orderId)) {
            return MallResult.build(HttpStatus.BAD_REQUEST.value(), ResponseMsgEnum.ORDER_INSERT_FAIL.getValue());
        } else {
            return MallResult.ok(orderId);
        }
    }
}
