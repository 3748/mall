package com.mall.manage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转
 *
 * @author gp6
 * @date 2018-07-07
 */
@Controller
@RequestMapping("page")
public class PageController {

    /**
     * 页面跳转
     *
     * @param pageName 要跳转的页面名称
     * @return String
     */
    @RequestMapping(value = "{pageName}", method = RequestMethod.GET)
    public String toPage(@PathVariable("pageName") String pageName) {
        return pageName;
    }
}