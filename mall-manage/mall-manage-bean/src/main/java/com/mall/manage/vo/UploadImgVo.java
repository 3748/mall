package com.mall.manage.vo;

/**
 * 图片上传返回实体类
 *
 * @author gp6
 * @date 2018-07-10
 */
public class UploadImgVo {
    private Integer error;

    private String url;

    private String width;

    private String height;

    public Integer getError() {
        return error;
    }

    public void setError(Integer error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }
}
