package com.mall.cart.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.bean.Cart;
import com.mall.common.bean.Item;
import com.mall.common.enums.KeywordEnum;
import com.mall.common.enums.NumberEnum;
import com.mall.common.utils.CookieUtils;
import com.mall.common.utils.DateTimeUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 购物车----用户未登录
 *
 * @author gp6
 * @date 2018-10-26
 */
@Service
public class CartCookieService {

    @Autowired
    private ItemService itemService;

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 将商品加入购物车
     *
     * @param itemId   商品id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception 异常信息
     */
    public void insertItemToCart(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> cartList = selectCartList(request);

        Cart cart = null;
        for (Cart c : cartList) {
            if (c.getItemId().equals(itemId)) {
                cart = c;
                break;
            }
        }

        if (null == cart) {
            cart = new Cart();
            cart.setCreateTime(DateTimeUtil.CURRENT_TIME);
            cart.setUpdateTime(cart.getCreateTime());

            Item item = itemService.selectItemById(itemId);
            cart.setItemId(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemPrice(item.getPrice());
            cart.setItemImage(item.getImage());
            cart.setNum(NumberEnum.ONE.getValue());

            cartList.add(cart);
        } else {
            cart.setNum(cart.getNum() + 1);
            cart.setUpdateTime(DateTimeUtil.CURRENT_TIME);
        }

        // 将商品数据写入Cookie
        CookieUtils.setCookie(request, response, KeywordEnum.MALL_CART_COOKIE.getValue(), OBJECT_MAPPER.writeValueAsString(cartList), NumberEnum.COOKIE_TIME.getValue(), true);
    }

    /**
     * 将商品加入购物车
     *
     * @param itemId   商品id
     * @param num      商品数量
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception 异常信息
     */
    public void updateNum(Long itemId, Integer num, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> cartList = selectCartList(request);

        Cart cart = null;
        for (Cart c : cartList) {
            if (c.getItemId().longValue() == itemId.longValue()) {
                cart = c;
                break;
            }
        }

        if (cart != null) {
            cart.setNum(num);
            cart.setUpdateTime(DateTimeUtil.CURRENT_TIME);
        } else {
            return;
        }
        CookieUtils.setCookie(request, response, KeywordEnum.MALL_CART_COOKIE.getValue(), OBJECT_MAPPER.writeValueAsString(cartList), NumberEnum.COOKIE_TIME.getValue(), true);
    }

    /**
     * 将商品从购物车删除
     *
     * @param itemId   商品id
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception 异常
     */
    public void delete(Long itemId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Cart> cartList = selectCartList(request);

        Cart cart = null;
        for (Cart c : cartList) {
            if (c.getItemId().longValue() == itemId.longValue()) {
                cart = c;
                cartList.remove(c);
                break;
            }
        }

        if (cart == null) {
            return;
        }

        CookieUtils.setCookie(request, response, KeywordEnum.MALL_CART_COOKIE.getValue(), OBJECT_MAPPER.writeValueAsString(cartList), NumberEnum.COOKIE_TIME.getValue(), true);
    }

    /**
     * @param request HttpServletRequest
     * @return List
     * @throws Exception 异常
     */
    private List<Cart> selectCartList(HttpServletRequest request) throws Exception {
        // 从Cookie中获取数据
        String jsonData = CookieUtils.getCookieValue(request, KeywordEnum.MALL_CART_COOKIE.getValue(), true);

        List<Cart> cartList;
        if (StringUtils.isEmpty(jsonData)) {
            cartList = new ArrayList<>();
        } else {
            // 序列化成为一个集合
            cartList = OBJECT_MAPPER.readValue(jsonData, OBJECT_MAPPER.getTypeFactory().constructCollectionType(List.class, Cart.class));
        }

        return cartList;
    }
}
