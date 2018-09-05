package com.mall.sso.controller;

import com.mall.common.bean.User;
import com.mall.common.constant.Constants;
import com.mall.common.enums.NumberEnum;
import com.mall.common.utils.CookieUtils;
import com.mall.sso.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
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
     * 用户登录
     *
     * @param user     登录时提供的用户信息
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ResponseEntity<Map<String,Object>>
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>(NumberEnum.MAP_INIT_SIZE.ordinal());
        try {
            String token = loginService.login(user.getUserName(), user.getPassword());
            if (StringUtils.isEmpty(token)) {
                result.put("data", " 哈哈~~~登录失败了");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }

            CookieUtils.setCookie(request, response, "TT_TOKEN", token);
        } catch (Exception e) {
            result.put("status", 500);
        }
        return ResponseEntity.ok(result);
    }

}
