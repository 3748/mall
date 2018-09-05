package com.mall.common.enums;

/**
 * 数字常量
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum NumberEnum {
    //Map初始化默认大小
    MAP_INIT_SIZE(16),

    //token有效时间,30分钟
    TOKEN_EXPIRE_TIME(60 * 30);

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
