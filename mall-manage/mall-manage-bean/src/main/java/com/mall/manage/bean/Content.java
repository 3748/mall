package com.mall.manage.bean;

import com.mall.common.bean.Base;

import javax.persistence.Table;

/**
 * 首页内容
 *
 * @author gp6
 * @date 2018-08-21
 */
@Table(name = "m_content")
public class Content extends Base {
	/**
	 *
	 */
	private Long id;

	/**
	 * 内容类目ID
	 */
	private Long contentCatId;

	/**
	 * 内容标题
	 */
	private String title;

	/**
	 * 子标题
	 */
	private String subTitle;

	/**
	 * 标题描述
	 */
	private String titleDesc;

	/**
	 * 链接
	 */
	private String url;

	/**
	 * 图片绝对路径
	 */
	private String pic;

	/**
	 * 图片2
	 */
	private String pic2;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 内容类目ID
	 *
	 * @return content_cat_id 内容类目ID
	 */
	public Long getContentCatId() {
		return contentCatId;
	}

	/**
	 * 内容类目ID
	 *
	 * @param contentCatId 内容类目ID
	 */
	public void setContentCatId(Long contentCatId) {
		this.contentCatId = contentCatId;
	}

	/**
	 * 内容标题
	 *
	 * @return title 内容标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 内容标题
	 *
	 * @param title 内容标题
	 */
	public void setTitle(String title) {
		this.title = title == null ? null : title.trim();
	}

	/**
	 * 子标题
	 *
	 * @return sub_title 子标题
	 */
	public String getSubTitle() {
		return subTitle;
	}

	/**
	 * 子标题
	 *
	 * @param subTitle 子标题
	 */
	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle == null ? null : subTitle.trim();
	}

	/**
	 * 标题描述
	 *
	 * @return title_desc 标题描述
	 */
	public String getTitleDesc() {
		return titleDesc;
	}

	/**
	 * 标题描述
	 *
	 * @param titleDesc 标题描述
	 */
	public void setTitleDesc(String titleDesc) {
		this.titleDesc = titleDesc == null ? null : titleDesc.trim();
	}

	/**
	 * 链接
	 *
	 * @return url 链接
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 链接
	 *
	 * @param url 链接
	 */
	public void setUrl(String url) {
		this.url = url == null ? null : url.trim();
	}

	/**
	 * 图片绝对路径
	 *
	 * @return pic 图片绝对路径
	 */
	public String getPic() {
		return pic;
	}

	/**
	 * 图片绝对路径
	 *
	 * @param pic 图片绝对路径
	 */
	public void setPic(String pic) {
		this.pic = pic == null ? null : pic.trim();
	}

	/**
	 * 图片2
	 *
	 * @return pic2 图片2
	 */
	public String getPic2() {
		return pic2;
	}

	/**
	 * 图片2
	 *
	 * @param pic2 图片2
	 */
	public void setPic2(String pic2) {
		this.pic2 = pic2 == null ? null : pic2.trim();
	}

	/**
	 * 内容
	 *
	 * @return content 内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 *
	 * @param content 内容
	 */
	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}
}