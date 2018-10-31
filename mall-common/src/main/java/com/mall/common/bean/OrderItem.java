package com.mall.common.bean;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单商品信息表
 *
 * @author gp6
 * @date 2018-10-12
 */
@Table(name = "m_order_item")
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 207005624974424619L;
    /**
     * 商品id
     */
    private String itemId;

    /**
     * 订单id
     */
    private String orderId;

    /**
     * 商品购买数量
     */
    private Integer num;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品单价
     */
    private Long price;

    /**
     * 商品总金额
     */
    private Long totalFee;

    /**
     * 商品图片地址
     */
    private String picPath;

    /**
     * 商品id
     *
     * @return item_id 商品id
     */
    public String getItemId() {
        return itemId;
    }

    /**
     * 商品id
     *
     * @param itemId 商品id
     */
    public void setItemId(String itemId) {
        this.itemId = itemId == null ? null : itemId.trim();
    }

    /**
     * 订单id
     *
     * @return order_id 订单id
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单id
     *
     * @param orderId 订单id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 商品购买数量
     *
     * @return num 商品购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 商品购买数量
     *
     * @param num 商品购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 商品标题
     *
     * @return title 商品标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 商品标题
     *
     * @param title 商品标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 商品单价
     *
     * @return price 商品单价
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 商品单价
     *
     * @param price 商品单价
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 商品总金额
     *
     * @return total_fee 商品总金额
     */
    public Long getTotalFee() {
        return totalFee;
    }

    /**
     * 商品总金额
     *
     * @param totalFee 商品总金额
     */
    public void setTotalFee(Long totalFee) {
        this.totalFee = totalFee;
    }

    /**
     * 商品图片地址
     *
     * @return pic_path 商品图片地址
     */
    public String getPicPath() {
        return picPath;
    }

    /**
     * 商品图片地址
     *
     * @param picPath 商品图片地址
     */
    public void setPicPath(String picPath) {
        this.picPath = picPath == null ? null : picPath.trim();
    }
}