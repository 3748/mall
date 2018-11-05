package com.mall.cart.service;

import com.github.abel533.entity.Example;
import com.mall.cart.mapper.CartMapper;
import com.mall.common.bean.Cart;
import com.mall.common.bean.Item;
import com.mall.common.bean.User;
import com.mall.common.enums.NumberEnum;
import com.mall.common.threadlocal.UserThreadLocal;
import com.mall.common.utils.DateTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 购物车----用户已登录
 *
 * @author gp6
 * @date 2018-10-26
 */
@Service
public class CartService {

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private ItemService itemService;

    /**
     * 将商品加入购物车中
     *
     * @param itemId 商品id
     */
    public void insertItemToCart(Long itemId) {
        User user = UserThreadLocal.get();
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(user.getId());
        Cart cart = cartMapper.selectOne(record);

        if (null == cart) {
            // 该商品在购物车中不存在,将商品加入购物车
            cart = new Cart();
            cart.setUserId(user.getId());
            cart.setCreateTime(DateTimeUtil.CURRENT_TIME);
            cart.setUpdateTime(cart.getCreateTime());

            Item item = itemService.selectItemById(itemId);

            cart.setItemId(itemId);
            cart.setItemTitle(item.getTitle());
            cart.setItemPrice(item.getPrice());
            cart.setItemImage(item.getImage());
            cart.setNum(NumberEnum.ONE.getValue());

            cartMapper.insert(cart);
        } else {
            // 该商品在购物车中存在,修改购物车中商品数量
            cart.setNum(cart.getNum() + 1);
            cart.setUpdateTime(DateTimeUtil.CURRENT_TIME);
            cartMapper.updateByPrimaryKeySelective(cart);
        }
    }

    /**
     * @param itemId 商品id
     * @param num    商品数量
     */
    public void updateNum(Long itemId, Integer num) {
        Cart cart = new Cart();
        cart.setNum(num);
        cart.setUpdateTime(DateTimeUtil.CURRENT_TIME);

        Example example = new Example(Cart.class);
        example.createCriteria().andEqualTo("itemId", itemId).andEqualTo("userId", UserThreadLocal.get().getId());
        this.cartMapper.updateByExampleSelective(cart, example);
    }

    /**
     * @param itemId 商品id
     */
    public void delete(Long itemId) {
        Cart record = new Cart();
        record.setItemId(itemId);
        record.setUserId(UserThreadLocal.get().getId());
        this.cartMapper.delete(record);
    }
}
