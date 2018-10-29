package com.mall.cart.handler.interceptor;

import com.mall.cart.service.UserService;
import com.mall.common.threadlocal.UserThreadLocal;
import com.mall.common.bean.User;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义拦截器
 *
 * @author gp6
 * @date 2018-10-16
 */
public class HandlerInterceptorImpl implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        // 清空当前线程中的User对象
        UserThreadLocal.remove();

        // 从Cookie中获取token
        String token = CookieUtils.getCookieValue(httpServletRequest, KeywordEnum.MALL_SSO_TOKEN.getValue());
        if (StringUtils.isEmpty(token)) {
            // Token为空,用户处于未登陆状态,放行
            return true;
        }

        // 调用接口,根据token获取user信息
        User user = userService.selectUserByToken(token);

        // user为null,登录失效,放行
        if (null == user) {
            return true;
        }

        // 将user对象放入ThreadLocal中
        UserThreadLocal.set(user);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        UserThreadLocal.set(null);
    }
}
