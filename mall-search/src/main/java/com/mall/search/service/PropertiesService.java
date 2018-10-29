package com.mall.search.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 获取env.properties中的信息
 *
 * @author gp6
 * @date 2018-10-29
 */
@Service
public class PropertiesService {
    @Value("${MANAGE_URL}")
    public String manageUrl;

    @Value("${MANAGE_ITEM_URL}")
    public String manageItemUrl;

}
