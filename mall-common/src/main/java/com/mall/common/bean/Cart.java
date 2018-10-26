package com.mall.common.bean;

import javax.persistence.Table;

/**
 * 购物车
 *
 * @author gp6
 * @date 2018-10-26
 */
@Table(name = "m_cart")
public class Cart extends Base {
    /**
     * 自增ID
     */
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品标题
     */
    private String itemTitle;

    /**
     * 商品主图
     */
    private String itemImage;

    /**
     * 商品价格，单位为：分
     */
    private Long itemPrice;

    /**
     * 购买数量
     */
    private Integer num;

    /**
     * 自增ID
     *
     * @return id 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 自增ID
     *
     * @param id 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户ID
     *
     * @return user_id 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 商品ID
     *
     * @return item_id 商品ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 商品ID
     *
     * @param itemId 商品ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 商品标题
     *
     * @return item_title 商品标题
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * 商品标题
     *
     * @param itemTitle 商品标题
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    /**
     * 商品主图
     *
     * @return item_image 商品主图
     */
    public String getItemImage() {
        return itemImage;
    }

    /**
     * 商品主图
     *
     * @param itemImage 商品主图
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage == null ? null : itemImage.trim();
    }

    /**
     * 商品价格，单位为：分
     *
     * @return item_price 商品价格，单位为：分
     */
    public Long getItemPrice() {
        return itemPrice;
    }

    /**
     * 商品价格，单位为：分
     *
     * @param itemPrice 商品价格，单位为：分
     */
    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * 购买数量
     *
     * @return num 购买数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 购买数量
     *
     * @param num 购买数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

}