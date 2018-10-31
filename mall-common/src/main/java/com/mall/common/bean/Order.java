package com.mall.common.bean;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 订单表
 *
 * @author gp6
 * @date 2018-10-16
 */
@Table(name = "m_order")
public class Order implements Serializable {
    private static final long serialVersionUID = -3738742377474116439L;
    /**
     * 订单id
     */
    @Id
    private String orderId;

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String payment;

    /**
     * 支付类型，1、在线支付，2、货到付款
     */
    private Integer paymentType;

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    private String postFee;

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     */
    private Integer status;

    /**
     * 订单创建时间
     */
    private Long createTime;

    /**
     * 订单更新时间
     */
    private Long updateTime;

    /**
     * 付款时间
     */
    private Long paymentTime;

    /**
     * 发货时间
     */
    private Long consignTime;

    /**
     * 交易完成时间
     */
    private Long finishTime;

    /**
     * 交易关闭时间
     */
    private Long closeTime;

    /**
     * 物流名称
     */
    private String shippingName;

    /**
     * 物流单号
     */
    private String shippingCode;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 买家留言
     */
    private String buyerMessage;

    /**
     * 买家昵称
     */
    private String buyerNick;

    /**
     * 买家是否已经评价 0:未评价 1:已评价
     */
    private Integer buyerAssess;

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
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @return payment 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public String getPayment() {
        return payment;
    }

    /**
     * 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @param payment 实付金额。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    /**
     * 支付类型，1、在线支付，2、货到付款
     *
     * @return payment_type 支付类型，1、在线支付，2、货到付款
     */
    public Integer getPaymentType() {
        return paymentType;
    }

    /**
     * 支付类型，1、在线支付，2、货到付款
     *
     * @param paymentType 支付类型，1、在线支付，2、货到付款
     */
    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @return post_fee 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public String getPostFee() {
        return postFee;
    }

    /**
     * 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     *
     * @param postFee 邮费。精确到2位小数;单位:元。如:200.07，表示:200元7分
     */
    public void setPostFee(String postFee) {
        this.postFee = postFee == null ? null : postFee.trim();
    }

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     *
     * @return status 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     *
     * @param status 状态：1、未付款，2、已付款，3、未发货，4、已发货，5、交易成功，6、交易关闭
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 订单创建时间
     *
     * @return create_time 订单创建时间
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 订单创建时间
     *
     * @param createTime 订单创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 订单更新时间
     *
     * @return update_time 订单更新时间
     */
    public Long getUpdateTime() {
        return updateTime;
    }

    /**
     * 订单更新时间
     *
     * @param updateTime 订单更新时间
     */
    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 付款时间
     *
     * @return payment_time 付款时间
     */
    public Long getPaymentTime() {
        return paymentTime;
    }

    /**
     * 付款时间
     *
     * @param paymentTime 付款时间
     */
    public void setPaymentTime(Long paymentTime) {
        this.paymentTime = paymentTime;
    }

    /**
     * 发货时间
     *
     * @return consign_time 发货时间
     */
    public Long getConsignTime() {
        return consignTime;
    }

    /**
     * 发货时间
     *
     * @param consignTime 发货时间
     */
    public void setConsignTime(Long consignTime) {
        this.consignTime = consignTime;
    }

    /**
     * 交易完成时间
     *
     * @return finish_time 交易完成时间
     */
    public Long getFinishTime() {
        return finishTime;
    }

    /**
     * 交易完成时间
     *
     * @param finishTime 交易完成时间
     */
    public void setFinishTime(Long finishTime) {
        this.finishTime = finishTime;
    }

    /**
     * 交易关闭时间
     *
     * @return close_time 交易关闭时间
     */
    public Long getCloseTime() {
        return closeTime;
    }

    /**
     * 交易关闭时间
     *
     * @param closeTime 交易关闭时间
     */
    public void setCloseTime(Long closeTime) {
        this.closeTime = closeTime;
    }

    /**
     * 物流名称
     *
     * @return shipping_name 物流名称
     */
    public String getShippingName() {
        return shippingName;
    }

    /**
     * 物流名称
     *
     * @param shippingName 物流名称
     */
    public void setShippingName(String shippingName) {
        this.shippingName = shippingName == null ? null : shippingName.trim();
    }

    /**
     * 物流单号
     *
     * @return shipping_code 物流单号
     */
    public String getShippingCode() {
        return shippingCode;
    }

    /**
     * 物流单号
     *
     * @param shippingCode 物流单号
     */
    public void setShippingCode(String shippingCode) {
        this.shippingCode = shippingCode == null ? null : shippingCode.trim();
    }

    /**
     * 用户id
     *
     * @return user_id 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 买家留言
     *
     * @return buyer_message 买家留言
     */
    public String getBuyerMessage() {
        return buyerMessage;
    }

    /**
     * 买家留言
     *
     * @param buyerMessage 买家留言
     */
    public void setBuyerMessage(String buyerMessage) {
        this.buyerMessage = buyerMessage == null ? null : buyerMessage.trim();
    }

    /**
     * 买家昵称
     *
     * @return buyer_nick 买家昵称
     */
    public String getBuyerNick() {
        return buyerNick;
    }

    /**
     * 买家昵称
     *
     * @param buyerNick 买家昵称
     */
    public void setBuyerNick(String buyerNick) {
        this.buyerNick = buyerNick == null ? null : buyerNick.trim();
    }

    /**
     * 买家是否已经评价 0:未评价 1:已评价
     *
     * @return buyer_assess 买家是否已经评价 0:未评价 1:已评价
     */
    public Integer getBuyerAssess() {
        return buyerAssess;
    }

    /**
     * 买家是否已经评价 0:未评价 1:已评价
     *
     * @param buyerAssess 买家是否已经评价 0:未评价 1:已评价
     */
    public void setBuyerAssess(Integer buyerAssess) {
        this.buyerAssess = buyerAssess;
    }
}