package com.mall.sso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.sso.bean.User;
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

	public Integer checkRegister(String param, Integer type) {
		User user = new User();

		switch (type.intValue()) {
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

}
