package com.mall.common.bean;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Table;

/**
 * 用户
 *
 * @author gp6
 * @date 2018-08-23
 */
@Table(name = "m_user")
public class User extends Base {
    /**
     *
     */
    private Long id;

    /**
     * 用户名
     */
    @Length(min = 3, max = 20, message = "用户名的长度必须在3-20位之间!")
    private String userName;

    /**
     * 密码，加密存储
     * <p>
     * json序列化时,忽略此字段(@JsonIgnore) 如果加入该注解,@RequestBody User 也不会获取到该字段的值
     */
    @Length(min = 6, max = 20, message = "密码的长度必须在6-20位之间!")
    private String password;

    /**
     * 注册手机号
     */
    @Length(min = 11, max = 11, message = "手机号长度必须为11位!")
    private String mobile;

    /**
     * 注册邮箱
     */
    @Email(message = "邮箱格式不正确!")
    private String email;


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
     * @param userName 用户名
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
     * @param password 密码，加密存储
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
     * @param mobile 注册手机号
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
     * @param email 注册邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}