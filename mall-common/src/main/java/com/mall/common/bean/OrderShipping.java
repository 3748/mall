package com.mall.common.bean;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 收货信息表
 *
 * @author gp6
 * @date 2018-10-12
 */
@Table(name = "m_order_shipping")
public class OrderShipping extends Base implements Serializable {
    private static final long serialVersionUID = -4748564402949367938L;
    /**
     * 订单ID
     */
    @Length(max = 20)
    private String orderId;

    /**
     * 收货人全名
     */
    private String receiverName;

    /**
     * 固定电话
     */
    @Length(max = 20)
    private String receiverPhone;

    /**
     * 移动电话
     */
    @Length(max = 30)
    private String receiverMobile;

    /**
     * 省份
     */
    @Length(max = 10)
    private String receiverState;

    /**
     * 城市
     */
    @Length(max = 10)
    private String receiverCity;

    /**
     * 区/县
     */
    @Length(max = 20)
    private String receiverDistrict;

    /**
     * 收货地址，如：xx路xx号
     */
    @Length(max = 200)
    private String receiverAddress;

    /**
     * 邮政编码,如：310001
     */
    @Length(max = 6)
    private String receiverZip;

    /**
     * 订单ID
     *
     * @return order_id 订单ID
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 收货人全名
     *
     * @return receiver_name 收货人全名
     */
    public String getReceiverName() {
        return receiverName;
    }

    /**
     * 收货人全名
     *
     * @param receiverName 收货人全名
     */
    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName == null ? null : receiverName.trim();
    }

    /**
     * 固定电话
     *
     * @return receiver_phone 固定电话
     */
    public String getReceiverPhone() {
        return receiverPhone;
    }

    /**
     * 固定电话
     *
     * @param receiverPhone 固定电话
     */
    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone == null ? null : receiverPhone.trim();
    }

    /**
     * 移动电话
     *
     * @return receiver_mobile 移动电话
     */
    public String getReceiverMobile() {
        return receiverMobile;
    }

    /**
     * 移动电话
     *
     * @param receiverMobile 移动电话
     */
    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile == null ? null : receiverMobile.trim();
    }

    /**
     * 省份
     *
     * @return receiver_state 省份
     */
    public String getReceiverState() {
        return receiverState;
    }

    /**
     * 省份
     *
     * @param receiverState 省份
     */
    public void setReceiverState(String receiverState) {
        this.receiverState = receiverState == null ? null : receiverState.trim();
    }

    /**
     * 城市
     *
     * @return receiver_city 城市
     */
    public String getReceiverCity() {
        return receiverCity;
    }

    /**
     * 城市
     *
     * @param receiverCity 城市
     */
    public void setReceiverCity(String receiverCity) {
        this.receiverCity = receiverCity == null ? null : receiverCity.trim();
    }

    /**
     * 区/县
     *
     * @return receiver_district 区/县
     */
    public String getReceiverDistrict() {
        return receiverDistrict;
    }

    /**
     * 区/县
     *
     * @param receiverDistrict 区/县
     */
    public void setReceiverDistrict(String receiverDistrict) {
        this.receiverDistrict = receiverDistrict == null ? null : receiverDistrict.trim();
    }

    /**
     * 收货地址，如：xx路xx号
     *
     * @return receiver_address 收货地址，如：xx路xx号
     */
    public String getReceiverAddress() {
        return receiverAddress;
    }

    /**
     * 收货地址，如：xx路xx号
     *
     * @param receiverAddress 收货地址，如：xx路xx号
     */
    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress == null ? null : receiverAddress.trim();
    }

    /**
     * 邮政编码,如：310001
     *
     * @return receiver_zip 邮政编码,如：310001
     */
    public String getReceiverZip() {
        return receiverZip;
    }

    /**
     * 邮政编码,如：310001
     *
     * @param receiverZip 邮政编码,如：310001
     */
    public void setReceiverZip(String receiverZip) {
        this.receiverZip = receiverZip == null ? null : receiverZip.trim();
    }
}