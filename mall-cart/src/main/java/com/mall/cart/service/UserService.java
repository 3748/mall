package com.mall.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.User;
import com.mall.common.utils.HttpClientUtil;
import org.apache.commons.lang3.StringUtils;
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

    @Autowired
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public User selectUserByToken(String token) {
        String url = propertiesService.mallSsoUrl + propertiesService.mallSsoUserUrl + token;
        String getResponse;
        try {
            getResponse = httpClientUtil.doGet(url);
            if (StringUtils.isNotEmpty(getResponse)) {
                return OBJECT_MAPPER.readValue(getResponse, User.class);
            }
        } catch (Exception e) {
            LOGGER.error("根据Token获取用户信息失败,原因:", e.getMessage());
        }
        return null;
    }
}