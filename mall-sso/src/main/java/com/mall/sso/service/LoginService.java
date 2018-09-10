package com.mall.sso.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.User;
import com.mall.common.enums.NumberEnum;
import com.mall.common.utils.RedisUtil;
import com.mall.sso.mapper.UserMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gp6
 * @date 2018/9/3
 */
@Service
public class LoginService {

    private final static ObjectMapper MAPPER = new ObjectMapper();

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private UserMapper userMapper;

    public String login(String username, String password) throws Exception {
        User record = new User();
        record.setUserName(username);

        User user = userMapper.selectOne(record);
        if (null == user) {
            return null;
        }

        //获取密码与传入密码比较
        if (!StringUtils.equals(DigestUtils.md5Hex(password), user.getPassword())) {
            return null;
        }

        String token = DigestUtils.md5Hex(username + System.currentTimeMillis());

        //设置token和过期时间
        redisUtil.set("TOKEN_" + token, MAPPER.writeValueAsString(user), NumberEnum.TOKEN_EXPIRE_TIME.getValue());

        return token;
    }
}
