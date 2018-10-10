package com.mall.common.enums;

/**
 * 图片类型
 *
 * @author gp6
 * @date 2018/9/5
 */
public enum ImageTypeEnum {

    // 对应文件后缀
    BMP(".bmp"), JPG(".jpg"), JPEG(".jpeg"), GIF(".gif"), PNG(".png");

    private String value;

    ImageTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
