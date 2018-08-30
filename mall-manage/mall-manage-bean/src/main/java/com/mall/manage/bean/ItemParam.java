package com.mall.manage.bean;

import javax.persistence.Table;

/**
 * 商品规格参数
 *
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "m_item_param")
public class ItemParam extends Base {
    /**
     *
     */
    private Long id;

    /**
     * 商品ID
     */
    private Long itemId;

    /**
     * 参数数据，格式为json格式
     */
    private String paramData;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * 参数数据，格式为json格式
     *
     * @return param_data 参数数据，格式为json格式
     */
    public String getParamData() {
        return paramData;
    }

    /**
     * 参数数据，格式为json格式
     *
     * @param paramData 参数数据，格式为json格式
     */
    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}