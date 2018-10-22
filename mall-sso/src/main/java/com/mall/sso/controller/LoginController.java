package com.mall.sso.controller;

import com.mall.common.bean.User;
import com.mall.common.enums.NumberEnum;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.utils.CookieUtils;
import com.mall.sso.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关接口
 *
 * @author gp6
 * @date 2018/9/3
 */
@Controller
@RequestMapping(value = {"login"})
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 跳转注册页面
     *
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 用户登录
     *
     * @param user     登录时提供的用户信息
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>(NumberEnum.MAP_INIT_SIZE.getValue());
        try {
            String token = loginService.login(user.getUserName(), user.getPassword());
            if (StringUtils.isEmpty(token)) {
                result.put("data", " 哈哈~~~登录失败了");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }

            CookieUtils.setCookie(request, response, KeywordEnum.MALL_SSO_TOKEN.getValue(), token);
        } catch (Exception e) {
            result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = {"{token}"}, method = RequestMethod.GET)
    public ResponseEntity<User> queryUserByToken(@PathVariable("token") String token) {
        User user = new User();
        user.setUserName(token + "该服务没有了，以后别调用了，请访问ssoquery.mall.com或dubbo中的服务。");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
    }
}
