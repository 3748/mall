package com.mall.common.enums;

/**
 * 关键字枚举
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum KeywordEnum {
    /**
     * Redis定义key的规则:项目名_模块名_业务名
     */
    MALL_WEB_ITEM_DETAIL("Web-商品详情Key", "MALL_WEB_ITEM_DETAIL"),

    MALL_MANAGE_ITEM_DETAIL("Manage-商品详情Key", "MALL_MANAGE_ITEM_DETAIL"),

    MALL_MANAGE_ITEM_CAT_ALL("Manage-商品类目Key", "MALL_MANAGE_ITEM_CAT_ALL"),

    MALL_SSO_LOGIN_TOKEN("Redis中Token Key", "MALL_SSO_LOGIN_TOKEN"),

    /**
     * Token
     */
    MALL_SSO_TOKEN("登录系统Token", "MALL_SSO_TOKEN"),

    /**
     * Cookie
     */
    MALL_CART_COOKIE("购物车系统Cookie","MALL_CART_COOKIE"),

    /**
     * MQ
     */
    MQ_TYPE_UPDATE("MQ操作类型--update", "update"),
    MQ_TYPE_INSERT("MQ操作类型--insert", "insert"),
    MQ_TYPE_DELETE("MQ操作类型--delete", "delete"),


    /**
     * 返回值中的状态
     */
    CODE("返回值中的Code", "code"),

    STSTUS("返回值中的Status", "status");

    private String name;
    private String value;

    KeywordEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }
}
