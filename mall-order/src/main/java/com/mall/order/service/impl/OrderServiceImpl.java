package com.mall.order.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Order;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.ResponseMsgEnum;
import com.mall.common.model.OrderModel;
import com.mall.common.utils.DateTimeUtil;
import com.mall.common.utils.ValidateUtil;
import com.mall.common.vo.MallResult;
import com.mall.order.mapper.OrderMapper;
import com.mall.order.pojo.PageResult;
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
    public MallResult insertOrderModel(String json) {

        OrderModel orderModel;
        try {
            orderModel = OBJECT_MAPPER.readValue(json, OrderModel.class);

            // 校验Order对象
            ValidateUtil.validate(orderModel);
        } catch (Exception e) {
            return MallResult.build(HttpStatus.BAD_REQUEST.value(), ResponseMsgEnum.REQUEST_PARAMETER_ERROR.getValue());
        }

        try {
            // 生成订单ID,规则为：userId+当前时间戳
            String orderId = orderModel.getUserId() + "" + System.currentTimeMillis();
            orderModel.setOrderId(orderId);

            // 设置订单的初始状态为未付款
            orderModel.setStatus(NumberEnum.ORDER_STATUS_UNPAID.getValue());
            orderModel.setCreateTime(DateTimeUtil.CURRENTTIME);
            orderModel.setUpdateTime(orderModel.getCreateTime());

            // 设置买家评价状态，初始为未评价
            orderModel.setBuyerAssess(NumberEnum.BUYER_UNASSESS.getValue());

            // 一次将数据插入3张表
            orderMapper.insertOrderInfo(orderModel);

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

            return MallResult.ok(orderId);
        } catch (Exception e) {
            LOGGER.error(ResponseMsgEnum.ORDER_INSERT_FAIL.getValue(), e.getMessage());
        }
        return MallResult.build(HttpStatus.BAD_REQUEST.value(), ResponseMsgEnum.ORDER_INSERT_FAIL.getValue());
    }

    @Override
    public Order selectOrderById(String orderId) {
        return orderMapper.selectOrderById(orderId);
    }

    @Override
    public PageResult<Order> selectOrderByUserNameAndPage(String buyerNick, Integer page, Integer count) {
        return null;
        //orderDao.queryOrderByUserNameAndPage(buyerNick, page, count);
    }

    @Override
    public MallResult updateOrderStatus(String json) {
        Order order;
        try {
            order = OBJECT_MAPPER.readValue(json, Order.class);
        } catch (Exception e) {
            LOGGER.error(ResponseMsgEnum.ORDER_UPDATE_FAIL.getValue(), e.getMessage());
            return MallResult.build(HttpStatus.BAD_REQUEST.value(), ResponseMsgEnum.REQUEST_PARAMETER_ERROR.getValue());
        }
        return null;
    }
}
