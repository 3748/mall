package com.mall.sso.query.provider.controller;

import com.mall.common.bean.User;
import com.mall.sso.query.provider.service.impl.UserServiceApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 用户管理
 *
 * @author gp6
 * @date 2018-10-31
 */
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserServiceApiImpl userServiceApiImpl;

    /**
     * 根据token查询用户信息
     *
     * @param token 登录token
     * @return 用户信息
     */
    @RequestMapping(value = "{token}", method = RequestMethod.GET)
    public ResponseEntity<User> queryUserByToken(@PathVariable("token") String token) {
        try {
            User user = userServiceApiImpl.selectUserByToken(token);
            if (null == user) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
