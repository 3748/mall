package com.mall.common.bean;

import org.apache.solr.client.solrj.beans.Field;

/**
 * 基础实体类
 *
 * @author gp6
 * @date 2018-08-22
 */
public class Base {
    /**
     * 创建时间
     */
    private Long createTime;

    /**
     * 更新时间
     */
    @Field("updateTime")
    private Long updateTime;

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
