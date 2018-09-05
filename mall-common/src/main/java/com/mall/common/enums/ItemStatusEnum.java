package com.mall.common.enums;

/**
 * 商品状态
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum ItemStatusEnum {
    //1:正常  2:下架  3:删除
    NORMAL(1), STOP(2), DELETE(3);

    private int value;

    ItemStatusEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
