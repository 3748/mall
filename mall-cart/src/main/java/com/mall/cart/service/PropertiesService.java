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
    @Value("${MALL_SSO_URL}")
    public String mallSsoUrl;

    @Value("${MALL_ORDER_URL}")
    public String mallOrderUrl;

    @Value("MALL_SSO_USER_URL")
    public String mallSsoUserUrl;

}
