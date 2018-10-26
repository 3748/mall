package com.mall.cart.controller;

import com.mall.cart.service.CartCookieService;
import com.mall.cart.service.CartService;
import com.mall.common.bean.User;
import com.mall.common.threadlocal.UserThreadLocal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车
 *
 * @author gp6
 * @date 2018-10-26
 */
@Controller
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartCookieService cartCookieService;

    /**
     * @param itemId   商品id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Http状态码
     */
    @RequestMapping(value = {"insert/{itemId}"}, method = RequestMethod.GET)
    public ResponseEntity<Void> insertItemToCart(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();

        if (null == user) {
            // 用户未登录
            try {
                cartCookieService.insertItemToCart(itemId, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 用户已登录
            cartService.insertItemToCart(itemId);
        }
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    /**
     * @param itemId   商品id
     * @param num      商品数量
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Http状态码
     */
    @RequestMapping(value = {"update/num/{itemId}/{num}"}, method = RequestMethod.POST)
    public ResponseEntity<Void> updateNum(@PathVariable("itemId") Long itemId, @PathVariable("num") Integer num, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        if (null == user) {
            try {
                cartCookieService.updateNum(itemId, num, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            cartService.updateNum(itemId, num);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * @param itemId   商品id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return Http状态码
     */
    @RequestMapping(value = {"delete/{itemId}"}, method = RequestMethod.GET)
    public String delete(@PathVariable("itemId") Long itemId, HttpServletRequest request, HttpServletResponse response) {
        User user = UserThreadLocal.get();
        if (null == user) {
            try {
                cartCookieService.delete(itemId, request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            cartService.delete(itemId);
        }

        return "redirect:/cart/list.html";
    }
}
