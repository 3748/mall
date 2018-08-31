package com.mall.common.bean;

import com.mall.common.bean.Base;

import javax.persistence.Table;

/**
 * 商品类目
 *
 * @author gp6
 * @date 2018-07-09
 */
@Table(name = "m_item_cat")
public class ItemCat extends Base {
    /**
     * 类目ID
     */
    private Long id;

    /**
     * 父类目ID=0时，代表的是一级的类目
     */
    private Long parentId;

    /**
     * 类目名称
     */
    private String name;

    /**
     * 类目状态, 1:正常 2:删除
     */
    private Integer status;

    /**
     * 排列序号，表示同级类目的展现次序，如数值相等则按名称次序排列。取值范围:大于零的整数
     */
    private Integer sortOrder;

    /**
     * 该类目是否为父类目，1:是，0:否
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
     * 类目名称
     *
     * @return name 类目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 类目名称
     *
     * @param name 类目名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 类目状态, 1:正常 2:删除
     *
     * @return status 类目状态, 1:正常 2:删除
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 类目状态, 1:正常 2:删除
     *
     * @param status 类目状态, 1:正常 2:删除
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
     * 该类目是否为父类目，1:是，0:否
     *
     * @return is_parent 该类目是否为父类目，1:是，0:否
     */
    public Integer getIsParent() {
        return isParent;
    }

    /**
     * 该类目是否为父类目，1:是，0:否
     *
     * @param isParent 该类目是否为父类目，1:是，0:否
     */
    public void setIsParent(Integer isParent) {
        this.isParent = isParent;
    }
}