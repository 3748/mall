package com.mall.sso.service;

import com.mall.common.utils.DateTimeUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.bean.User;
import com.mall.sso.mapper.UserMapper;

/**
 * 用户注册
 * 
 * @author gp6
 * @date 2018-08-23
 */
@Service
public class RegisterService {
	@Autowired
	private UserMapper userMapper;

	public Integer registerCheck(String param, Integer type) {
		User user = new User();

		switch (type) {
		case 1:
			user.setUserName(param);
			break;
		case 2:
			user.setMobile(param);
			break;
		case 3:
			user.setEmail(param);
			break;
		default:
			return 0;
		}
		return userMapper.selectCount(user);
	}

	public Boolean doRegister(User user) {
		user.setCreateTime(DateTimeUtil.CURRENTTIME);
		user.setUpdateTime(DateTimeUtil.CURRENTTIME);
		// 密码加密
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		userMapper.insert(user);
		return true;
	}
}
