package com.mall.sso.bean;

import javax.persistence.Table;

/**
 * 用户
 * 
 * @author gp6
 * @date 2018-08-23
 */
@Table(name = "m_user")
public class User {
	/**
	 * 
	 */
	private Long id;

	/**
	 * 用户名
	 */
	private String userName;

	/**
	 * 密码，加密存储
	 */
	private String password;

	/**
	 * 注册手机号
	 */
	private String mobile;

	/**
	 * 注册邮箱
	 */
	private String email;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 修改时间
	 */
	private Long updateTime;

	/**
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 用户名
	 * 
	 * @return user_name 用户名
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * 用户名
	 * 
	 * @param userName
	 *            用户名
	 */
	public void setUserName(String userName) {
		this.userName = userName == null ? null : userName.trim();
	}

	/**
	 * 密码，加密存储
	 * 
	 * @return password 密码，加密存储
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 密码，加密存储
	 * 
	 * @param password
	 *            密码，加密存储
	 */
	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	/**
	 * 注册手机号
	 * 
	 * @return mobile 注册手机号
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 注册手机号
	 * 
	 * @param mobile
	 *            注册手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile == null ? null : mobile.trim();
	}

	/**
	 * 注册邮箱
	 * 
	 * @return email 注册邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 注册邮箱
	 * 
	 * @param email
	 *            注册邮箱
	 */
	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	/**
	 * 创建时间
	 * 
	 * @return create_time 创建时间
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * 创建时间
	 * 
	 * @param createTime
	 *            创建时间
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * 修改时间
	 * 
	 * @return update_time 修改时间
	 */
	public Long getUpdateTime() {
		return updateTime;
	}

	/**
	 * 修改时间
	 * 
	 * @param updateTime
	 *            修改时间
	 */
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
}