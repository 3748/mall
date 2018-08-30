package com.mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mall.sso.service.RegisterService;

/**
 * 注册相关功能
 *
 * @author gp6
 * @date 2018-08-23
 */
@RequestMapping({"register"})
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 跳转注册页面
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String register() {
        return "register";
    }

    /**
     * 检查注册数据是否可用
     *
     * @param param 1:用户名 2:手机号 3:邮箱地址
     * @param type  代表param类型
     * @return
     */
    @RequestMapping(value = {"{param}/{type}"}, method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkRegister(@PathVariable("param") String param,
                                                 @PathVariable("type") Integer type) {
        try {
            Integer count = registerService.checkRegister(param, type);
            if (0 == count) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }
}
