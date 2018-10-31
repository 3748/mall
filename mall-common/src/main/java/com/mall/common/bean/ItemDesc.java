package com.mall.common.bean;

import javax.persistence.Table;
import java.io.Serializable;

/**
 * 商品描述
 *
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "m_item_desc")
public class ItemDesc extends Base implements Serializable {
    private static final long serialVersionUID = -9074471869209332600L;
    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 商品描述
     */
    private String itemDesc;

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
     * 商品描述
     *
     * @return item_desc 商品描述
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * 商品描述
     *
     * @param itemDesc 商品描述
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}