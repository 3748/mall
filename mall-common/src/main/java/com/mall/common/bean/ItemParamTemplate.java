package com.mall.common.bean;

import javax.persistence.Table;

/**
* 商品规格参数模板
*
* @author gp6
* @date 2018-09-27
*/
@Table(name = "m_item_param_template")
public class ItemParamTemplate {
    /**
     * 
     */
    private Long id;

    /**
     * 商品类目ID
     */
    private Long catId;

    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 修改时间
     */
    private Long updatedTime;

    /**
     * 参数数据，格式为json格式
     */
    private String paramData;

    /**
     * 
     * @return id 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id 商品类目ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 商品类目ID
     * @return cat_id 商品类目ID
     */
    public Long getCatId() {
        return catId;
    }

    /**
     * 商品类目ID
     * @param catId 商品类目ID
     */
    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 
     * @return create_time 
     */
    public Long getCreateTime() {
        return createTime;
    }

    /**
     * 
     * @param createTime 创建时间
     */
    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    /**
     * 
     * @return updated_time 
     */
    public Long getUpdatedTime() {
        return updatedTime;
    }

    /**
     * 
     * @param updatedTime 修改时间
     */
    public void setUpdatedTime(Long updatedTime) {
        this.updatedTime = updatedTime;
    }

    /**
     * 参数数据，格式为json格式
     * @return param_data 参数数据，格式为json格式
     */
    public String getParamData() {
        return paramData;
    }

    /**
     * 参数数据，格式为json格式
     * @param paramData 参数数据，格式为json格式
     */
    public void setParamData(String paramData) {
        this.paramData = paramData == null ? null : paramData.trim();
    }
}