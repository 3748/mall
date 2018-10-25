package com.mall.order.controller;

import com.mall.common.bean.Order;
import com.mall.common.response.MallResponse;
import com.mall.order.pojo.PageResponse;
import com.mall.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理
 *
 * @author gp6
 * @date 2018-10-15
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 创建订单
     *
     * @param json 订单数据
     * @return MallResponse
     */
    @ResponseBody
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public MallResponse insertOrderRequest(@RequestBody String json) {
        return orderService.insertOrderRequest(json);
    }

    /**
     * 根据订单ID查询订单
     *
     * @param orderId 订单ID
     * @return Order 订单信息
     */
    @ResponseBody
    @RequestMapping(value = "/select/{orderId}", method = RequestMethod.GET)
    public Order selectOrderById(@PathVariable("orderId") String orderId) {
        return orderService.selectOrderById(orderId);
    }

    /**
     * 根据用户名分页查询订单
     *
     * @param buyerNick 用户名
     * @param pageNum   页码
     * @param pageSize  每页显示条数
     * @return PageResponse
     */
    @ResponseBody
    @RequestMapping("/select/{buyerNick}/{pageNum}/{pageSize}")
    public PageResponse<Order> selectOrderByUserNameAndPage(@PathVariable("buyerNick") String buyerNick, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        return orderService.selectOrderByUserNameAndPage(buyerNick, pageNum, pageSize);
    }

    /**
     * 修改订单状态
     *
     * @param json 订单数据
     * @return MallResponse
     */
    @ResponseBody
    @RequestMapping(value = "/updateOrderStatus", method = RequestMethod.POST)
    public MallResponse updateOrderStatus(@RequestBody String json) {
        return orderService.updateOrderStatus(json);
    }
}
