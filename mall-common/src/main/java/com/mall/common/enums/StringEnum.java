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
    // Redis中前台----商品详情Key
    MALL_WEB_ITEM_DETAIL("MALL_WEB_ITEM_DETAIL"),

    // Redis中后台----商品详情Key
    MALL_MANAGE_ITEM_DETAIL("MALL_MANAGE_ITEM_DETAIL"),

    // Redis中后台----商品类目Key
    MALL_MANAGE_ITEM_CAT_ALL("MALL_MANAGE_ITEM_CAT_ALL"),

    // Redis中单点登录系统----token Key
    MALL_SSO_LOGIN_TOKEN("MALL_SSO_LOGIN_TOKEN"),

    /**
     * MQ操作类型
     */
    // MQ----修改
    MQ_TYPE_UPDATE("update"),

    // MQ----插入
    MQ_TYPE_INSERT("insert"),

    // 登录系统Token
    MALL_SSO_TOKEN("MALL_SSO_TOKEN");

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
