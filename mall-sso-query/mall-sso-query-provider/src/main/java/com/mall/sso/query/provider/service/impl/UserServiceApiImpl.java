package com.mall.sso.query.provider.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.common.bean.User;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.enums.NumberEnum;
import com.mall.common.utils.RedisUtil;
import com.mall.sso.query.api.UserServiceApi;

/**
 * 用户dubbo服务提供实现
 *
 * @author gp6
 * @date 2018-10-31
 */
@Service
public class UserServiceApiImpl implements UserServiceApi {
    @Autowired
    private RedisUtil redisUtil;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public User selectUserByToken(String token) {
        String key = KeywordEnum.MALL_SSO_TOKEN.getValue() + token;
        try {
            String jsonData = redisUtil.get(key);
            if (StringUtils.isEmpty(jsonData)) {
                // 登录超时
                return null;
            }
            // 重新刷新用户的生存时间
            redisUtil.expire(key, NumberEnum.TOKEN_EXPIRE_TIME.getValue());
            return OBJECT_MAPPER.readValue(jsonData, User.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
