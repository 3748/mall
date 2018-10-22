package com.mall.order.mapper;

import java.util.Date;


import com.github.abel533.mapper.Mapper;
import com.mall.common.bean.Order;
import com.mall.common.request.OrderRequest;
import org.apache.ibatis.annotations.Param;


/**
 * @author gp6
 * @date 2018-10-16
 */
public interface OrderMapper extends Mapper<Order> {
    /**
     * 保存订单信息
     *
     * @param orderRequest 订单信息
     */
    void insertOrderInfo(OrderRequest orderRequest);

    OrderRequest selectOrderById(String orderId);

    void paymentOrderScan(@Param("date") Date date);

}
