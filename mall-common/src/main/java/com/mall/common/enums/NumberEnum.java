package com.mall.common.enums;

/**
 * 数字枚举
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum NumberEnum {
    //Map初始化默认大小
    MAP_INIT_SIZE(16),

    //Redis中token有效时间,30分钟
    TOKEN_EXPIRE_TIME(60 * 30),

    //Redis中商品信息有效时间,24小时
    ITEM_DETAIL_EXPIRE_TIME(60 * 60 * 24),

    //商品状态  1:正常  2:下架  3:删除
    ITEM_STATUS_NORMAL(1), ITEM_STATUS_STOP(2), ITEM_STATUS_DELETE(3);;

    private int value;

    NumberEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
