package com.mall.common.bean;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 首页内容分类
 *
 * @author gp6
 * @date 2018-08-21
 */
@Table(name = "m_content_cat")
public class ContentCat extends Base implements Serializable {
    private static final long serialVersionUID = -2144982985150792554L;
    /**
     * 类目ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Long parentId;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 状态。可选值:1(正常),2(删除)
     */
    private Integer status;

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sortOrder;

    /**
     * 该类目是否为父类目，1为true，0为false
     */
    private Integer isParent;

    /**
     * 类目ID
     *
     * @return id 类目ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 类目ID
     *
     * @param id 类目ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 父类目ID=0时，代表的是一级的类目
     *
     * @return parent_id 父类目ID=0时，代表的是一级的类目
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父类目ID=0时，代表的是一级的类目
     *
     * @param parentId 父类目ID=0时，代表的是一级的类目
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 分类名称
     *
     * @return name 分类名称
     */
    public String getName() {
        return name;
    }

    /**
     * 分类名称
     *
     * @param name 分类名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 状态。可选值:1(正常),2(删除)
     *
     * @return status 状态。可选值:1(正常),2(删除)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 状态。可选值:1(正常),2(删除)
     *
     * @param status 状态。可选值:1(正常),2(删除)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     *
     * @return sort_order 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     *
     * @param sortOrder 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    /**
     * 该类目是否为父类目，1为true，0为false
     *
     * @return is_parent 该类目是否为父类目，1为true，0为false
     */
    public Integer getIsParent() {
        return isParent;
    }

    /**
     * 该类目是否为父类目，1为true，0为false
     *
     * @param isParent 该类目是否为父类目，1为true，0为false
     */
    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }
}