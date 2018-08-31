package com.mall.manage.bean;

import com.mall.common.bean.Base;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商品
 *
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "m_item")
public class Item extends Base {
    /**
     * 商品id，同时也是商品编号
     * <p>
     * 返回自增主键 @GeneratedValue
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    /**
     * 商品标题
     */
    private String title;

    /**
     * 商品卖点
     */
    private String sellPoint;

    /**
     * 商品价格，单位为：分
     */
    private Long price;

    /**
     * 库存数量
     */
    private Integer num;

    /**
     * 商品条形码
     */
    private String barcode;

    /**
     * 商品图片
     */
    private String image;

    /**
     * 所属类目，叶子类目
     */
    private Integer itemCatId;

    /**
     * 商品状态，1-正常，2-下架，3-删除
     */
    private Integer status;

    /**
     * 商品id，同时也是商品编号
     *
     * @return id 商品id，同时也是商品编号
     */
    public Long getId() {
        return id;
    }

    /**
     * 商品id，同时也是商品编号
     *
     * @param id 商品id，同时也是商品编号
     */
    public void setId(Long id) {
        this.id = id;
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
     * 商品卖点
     *
     * @return sell_point 商品卖点
     */
    public String getSellPoint() {
        return sellPoint;
    }

    /**
     * 商品卖点
     *
     * @param sellPoint 商品卖点
     */
    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    /**
     * 商品价格，单位为：分
     *
     * @return price 商品价格，单位为：分
     */
    public Long getPrice() {
        return price;
    }

    /**
     * 商品价格，单位为：分
     *
     * @param price 商品价格，单位为：分
     */
    public void setPrice(Long price) {
        this.price = price;
    }

    /**
     * 库存数量
     *
     * @return num 库存数量
     */
    public Integer getNum() {
        return num;
    }

    /**
     * 库存数量
     *
     * @param num 库存数量
     */
    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * 商品条形码
     *
     * @return barcode 商品条形码
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * 商品条形码
     *
     * @param barcode 商品条形码
     */
    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    /**
     * 商品图片
     *
     * @return image 商品图片
     */
    public String getImage() {
        return image;
    }

    /**
     * 商品图片
     *
     * @param image 商品图片
     */
    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    /**
     * 所属类目，叶子类目
     *
     * @return itemCatId 所属类目，叶子类目
     */
    public Integer getItemCatId() {
        return itemCatId;
    }

    /**
     * 所属类目，叶子类目
     *
     * @param item_cat_id 所属类目，叶子类目
     */
    public void setItemCatId(Integer itemCatId) {
        this.itemCatId = itemCatId;
    }

    /**
     * 商品状态，1-正常，2-下架，3-删除
     *
     * @return status 商品状态，1-正常，2-下架，3-删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 商品状态，1-正常，2-下架，3-删除
     *
     * @param status 商品状态，1-正常，2-下架，3-删除
     */
    public void setStatus(Integer status) {
        this.status = status;
    }
}