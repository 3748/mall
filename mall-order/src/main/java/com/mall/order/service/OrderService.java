package com.mall.order.service;

import com.mall.common.bean.Order;
import com.mall.common.response.MallResponse;
import com.mall.order.pojo.PageResponse;

/**
 * 订单接口
 *
 * @author gp6
 * @date 2018-10-16
 */
public interface OrderService {

    /**
     * 创建订单
     *
     * @param json 订单信息
     * @return MallResponse
     */
    MallResponse insertOrderRequest(String json);

    /**
     * 根据订单ID查询订单
     *
     * @param orderId 订单号
     * @return Order 订单信息
     */
    Order selectOrderById(String orderId);

    /**
     * 根据用户名分页查询订单信息
     *
     * @param buyerNick 买家昵称，用户名
     * @param page      分页起始取数位置
     * @param count     查询数据条数
     * @return 分页结果集
     */
    PageResponse<Order> selectOrderByUserNameAndPage(String buyerNick, Integer page, Integer count);

    /**
     * 更改订单状态，由service层控制修改逻辑
     *
     * @param json 订单信息
     * @return MallResponse 返回值
     */
    MallResponse updateOrderStatus(String json);
}
