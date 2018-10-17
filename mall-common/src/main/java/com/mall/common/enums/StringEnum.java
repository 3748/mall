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
    MALL_WEB_ITEM_DETAIL("Web-商品详情Key", "MALL_WEB_ITEM_DETAIL"),

    MALL_MANAGE_ITEM_DETAIL("Manage-商品详情Key", "MALL_MANAGE_ITEM_DETAIL"),

    MALL_MANAGE_ITEM_CAT_ALL("商品类目Key", "MALL_MANAGE_ITEM_CAT_ALL"),

    MALL_SSO_LOGIN_TOKEN("Redis中Token Key", "MALL_SSO_LOGIN_TOKEN"),

    MQ_TYPE_UPDATE("MQ操作类型--update", "update"),

    MQ_TYPE_INSERT("MQ操作类型--insert", "insert"),

    MALL_SSO_TOKEN(" 登录系统Token", "MALL_SSO_TOKEN");

    private String name;
    private String value;

    StringEnum(String value) {
        this.value = value;
    }

    StringEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
