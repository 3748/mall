package com.mall.common.enums;

/**
 * 操作符枚举
 *
 * @author gp6
 * @date 2018-10-17
 */
public enum OperatorEnum {
    /**
     * 项目中用到的操作符
     */
    EQUAL("="), GREATER(">"), LESS("<"), GREATER_EQUAL(">="), LESS_EQUAL("<="), NOT_EQUAL("<>");

    private String value;

    OperatorEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
