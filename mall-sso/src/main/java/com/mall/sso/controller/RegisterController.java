package com.mall.sso.controller;

import com.mall.common.bean.User;
import com.mall.common.enums.NumberEnum;
import com.mall.sso.service.RegisterService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 注册相关功能
 *
 * @author gp6
 * @date 2018-08-23
 */
@RequestMapping({"register"})
@Controller
public class RegisterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    private RegisterService registerService;

    /**
     * 跳转注册页面
     *
     * @return String
     */
    @RequestMapping(method = RequestMethod.GET)
    public String toRegister() {
        return "register";
    }

    /**
     * 检查注册数据是否可用
     *
     * @param param 1:用户名 2:手机号 3:邮箱地址
     * @param type  代表param类型
     * @return ResponseEntity<Boolean>
     */
    @RequestMapping(value = {"{param}/{type}"}, method = RequestMethod.GET)
    public ResponseEntity<Boolean> selectCount(@PathVariable("param") String param,
                                               @PathVariable("type") Integer type) {
        try {
            Integer count = registerService.selectCount(param, type);
            if (NumberEnum.ZERO.getValue() == count) {
                return ResponseEntity.ok(true);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            LOGGER.error("账号信息校验失败,原因:{}" + e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
    }

    /**
     * 用户注册
     *
     * @param user          注册信息
     * @param bindingResult @Valid校验的结果
     * @return ResponseEntity
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody User user, BindingResult bindingResult) {
        Map<String, Object> result = new HashMap<>(NumberEnum.FOUR.getValue());

        // bindingResult接收@Valid校验的结果
        if (bindingResult.hasErrors()) {
            List<String> errorMsgs = new ArrayList<>();

            List<ObjectError> objectErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : objectErrors) {
                // 获取User中定义的message的内容
                String message = objectError.getDefaultMessage();
                errorMsgs.add(message);
            }

            result.put("status", HttpStatus.BAD_REQUEST.value());

            // StringUtils.join : 以指定字符将集合中的值进行连接
            result.put("data", "参数有误! " + StringUtils.join(errorMsgs, '|'));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }

        try {
            Boolean bool = registerService.register(user);
            if (bool) {
                result.put("status", HttpStatus.CREATED.value());
                return ResponseEntity.ok(result);
            } else {
                result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
                result.put("data", " 笑Skr人,居然注册失败了!");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            result.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
            result.put("data", "笑Skr人,居然注册失败了!");
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
