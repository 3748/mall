package com.mall.common.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 获取用户信息
 *
 * @author gp6
 * @date 2018-10-29
 */
public class UserUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);

    @Autowired
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 根据token获取User信息
     *
     * @param httpClientUtil HttpClientUtil
     * @param url            url
     * @return User
     */
    public static User selectUserByToken(HttpClientUtil httpClientUtil, String url) {
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
