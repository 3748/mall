package com.mall.web.service;

import com.mall.common.bean.User;
import com.mall.common.utils.HttpClientUtil;
import com.mall.common.utils.UserUtil;
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

    @Autowired
    private PropertiesService propertiesService;

    @Autowired
    private HttpClientUtil httpClientUtil;

    public User selectUserByToken(String token) {
        String url = propertiesService.ssoUrl + propertiesService.ssoUserUrl + token;
        User user =  UserUtil.selectUserByToken(httpClientUtil, url);
        return user;
    }
}