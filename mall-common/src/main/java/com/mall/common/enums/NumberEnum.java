package com.mall.common.enums;

/**
 * 数字枚举
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum NumberEnum {
    // 项目中用到的数字
    ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4),SEVEN(7),SIXTEEN(16),

    // Redis中登录系统
    TOKEN_EXPIRE_TIME("Token有效时间,30分钟", 60 * 30),

    // Redis
    ITEM_DETAIL_EXPIRE_TIME("商品信息有效时间,24小时", 60 * 60 * 24),

    // Redis中后台
    ITEM_CAT_EXPIRE_TIME("商品类目信息有效时间,一个月", 60 * 60 * 24 * 30 * 3),

    // 商品状态
    ITEM_STATUS_NORMAL("正常", 1), ITEM_STATUS_STOP("下架", 2), ITEM_STATUS_DELETE("删除", 3),

    // 内容类目状态
    CONCAT_CAT_STATUS_NORMAL("正常", 1), CONCAT_CAT_STATUS_DELETE("删除", 2),

    // 内容类目是否为父类目
    CONCAT_CAT_IS_NOT_PARENT("否", 0), CONCAT_CAT_IS_PARENT("是", 1),

    // 订单状态
    ORDER_STATUS_UNPAID("未付款", 1), ORDER_STATUS_PAID("已付款", 2), ORDER_STATUS_UNSHIPPING("未发货", 3),
    ORDER_STATUS_SHIPPING("已发货", 4), ORDER_STATUS_TRADE_SUCC("交易成功", 5), ORDER_STATUS_TRADE_CLOSE("交易关闭", 6),

    // 买家是否评价
    BUYER_UNASSESS("未评价", 0), BUYER_ASSESS("已评价", 1);

    private String name;

    private int value;

    NumberEnum(int value) {
        this.value = value;
    }

    NumberEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
