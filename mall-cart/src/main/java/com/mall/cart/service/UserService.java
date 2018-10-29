package com.mall.cart.service;

import com.mall.common.bean.User;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户管理
 *
 * @author gp6
 * @date 2018-10-16
 */
@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private HttpClientUtil httpClientUtil;

    public User selectUserByToken(String token) {
        String url = propertiesService.ssoUrl + propertiesService.ssoUserUrl + token;
        return UserUtil.selectUserByToken(httpClientUtil, url);
    }
}