package com.mall.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Order;
import com.mall.common.enums.NumberEnum;
import com.mall.common.request.OrderRequest;
import com.mall.common.response.PageResponse;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.utils.ValidateUtil;
import com.mall.common.response.MallResponse;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author gp6
 * @date 2018-10-15
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private OrderMapper orderMapper;

    // @Autowired
    // private RabbitTemplate rabbitTemplate;

    @Override
    public MallResponse insertOrderRequest(String json) {

        OrderRequest orderRequest;
        try {
            orderRequest = OBJECT_MAPPER.readValue(json, OrderRequest.class);

            // 校验Order对象
            ValidateUtil.validate(orderRequest);
        } catch (Exception e) {
            return MallResponse.build(HttpStatus.BAD_REQUEST.value(), "请求参数错误!");
        }

        try {
            // 生成订单ID,规则为：userId+当前时间戳
            String orderId = orderRequest.getUserId() + "" + System.currentTimeMillis();
            orderRequest.setOrderId(orderId);

            // 设置订单的初始状态为未付款
            orderRequest.setStatus(NumberEnum.ORDER_STATUS_UNPAID.getValue());
            orderRequest.setCreateTime(DateTimeUtil.CURRENTTIME);
            orderRequest.setUpdateTime(orderRequest.getCreateTime());

            // 设置买家评价状态，初始为未评价
            orderRequest.setBuyerAssess(NumberEnum.BUYER_UNASSESS.getValue());

            // 一次将数据插入3张表
            orderMapper.insertOrderInfo(orderRequest);

            // 持久化order对象
            // orderDao.createOrder(order);
            // 发送消息到RabbitMQ
            // Map<String, Object> msg = new HashMap<String, Object>(3);
            // msg.put("userId", order.getUserId());
            // msg.put("orderId", order.getOrderId());
            // List<Long> itemIds = new ArrayList<Long>(order.getOrderItems().size());
            // for (OrderItem orderItem : order.getOrderItems()) {
            //     itemIds.add(orderItem.getItemId());
            // }
            // msg.put("itemIds", itemIds);
            // this.rabbitTemplate.convertAndSend(objectMapper.writeValueAsString(msg));

            return MallResponse.ok(orderId);
        } catch (Exception e) {
            LOGGER.error("订单新增失败,原因:", e.getMessage());
        }
        return MallResponse.build(HttpStatus.BAD_REQUEST.value(), "订单新增失败!");
    }

    @Override
    public Order selectOrderById(String orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public PageResponse<Order> selectOrderByUserNameAndPage(String buyerNick, Integer page, Integer count) {
        return null;
        //orderDao.queryOrderByUserNameAndPage(buyerNick, page, count);
    }

    @Override
    public MallResponse updateOrderStatus(String json) {
        Order order;
        try {
            order = OBJECT_MAPPER.readValue(json, Order.class);
        } catch (Exception e) {
            LOGGER.error("订单修改失败,原因:", e.getMessage());
            return MallResponse.build(HttpStatus.BAD_REQUEST.value(), "订单修改失败!");
        }
        return null;
    }
}
