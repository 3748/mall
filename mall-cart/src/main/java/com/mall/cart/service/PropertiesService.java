package com.mall.cart.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 获取env.properties中的信息
 *
 * @author gp6
 * @date 2018-10-16
 */
@Service
public class PropertiesService {
    @Value("${SSO_URL}")
    public String ssoUrl;

    @Value("${ORDER_URL}")
    public String orderUrl;

    @Value("${SSO_USER_URL}")
    public String ssoUserUrl;

    @Value("${MANAGE_URL}")
    public String manageUrl;

    @Value("${MANAGE_ITEM_URL}")
    public String manageItemUrl;

}
