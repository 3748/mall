package com.mall.sso.query.api;

import com.mall.common.bean.User;

/**
 * 用户dubbo服务公用api
 *
 * @author gp6
 * @date 2018-10-31
 */
public interface UserServiceApi {
    /**
     * 根据token查询User对象
     *
     * @return 用户信息
     */
    public User selectUserByToken(String token);
}
