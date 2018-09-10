package com.mall.common.enums;

/**
 * 字符串枚举
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum StringEnum {
    /**
     * Redis定义key的规则:项目名_模块名_业务名
     */
    //Redis中前台商品详情Key
    MALL_WEB_ITEM_DETAIL("MALL_WEB_ITEM_DETAIL"),
    //Redis中后台商品详情Key
    MALL_MANAGE_ITEM_DETAIL("MALL_MANAGE_ITEM_DETAIL");

    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
