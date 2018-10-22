package com.mall.common.request;

import com.mall.common.bean.Order;
import com.mall.common.bean.OrderItem;
import com.mall.common.bean.OrderShipping;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

/**
 * 接收前台传入的订单信息类
 *
 * @author gp6
 * @date 2018-10-16
 */
public class OrderRequest extends Order {
    /**
     * 商品详情
     */
    @NotEmpty
    private List<OrderItem> orderItems;

    /**
     * 物流地址信息
     */
    private OrderShipping orderShipping;

    public OrderShipping getOrderShipping() {
        return orderShipping;
    }

    public void setOrderShipping(OrderShipping orderShipping) {
        this.orderShipping = orderShipping;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }


}
